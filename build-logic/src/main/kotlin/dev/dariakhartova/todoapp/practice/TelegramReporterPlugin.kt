package dev.dariakhartova.todoapp.practice

import TelegramApi
import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import dev.dariakhartova.todoapp.tasks.AnalyzeApkContentTask
import dev.dariakhartova.todoapp.tasks.ValidateApkSizeTask
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.create

class TelegramReporterPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val androidComponents =
            project.extensions.findByType(AndroidComponentsExtension::class.java)
                ?: throw GradleException("Android not found")

        val extension = project.extensions.create("tgReporter", TelegramExtension::class)
        val telegramApi = TelegramApi(HttpClient(OkHttp))

        androidComponents.onVariants { variant ->
            val artifacts = variant.artifacts.get(SingleArtifact.APK)

            val validateTaskProvider = project.tasks.register(
                "validateApkSizeFor${variant.name.capitalize()}",
                ValidateApkSizeTask::class.java,
                telegramApi,
            ).apply {
                configure {
                    token.set(extension.token)
                    chatId.set(extension.chatId)
                    apkDir.set(artifacts)
                    maxApkSize.set(extension.maxApkSize)
                }
            }

            project.tasks.register(
                "analyzeApkContentFor${variant.name.capitalize()}",
                AnalyzeApkContentTask::class.java,
                telegramApi,
            ).configure {
                dependsOn("createDebugApkListingFileRedirect")
                token.set(extension.token)
                chatId.set(extension.chatId)
                apkDir.set(artifacts)
                projectDir.set(project.projectDir)
            }

            project.tasks.register(
                "reportTelegramApkFor${variant.name.capitalize()}",
                TelegramReporterTask::class.java,
                telegramApi
            ).configure {
                val sizeStr: Property<String> = project.objects.property(String::class.java)
                sizeStr.set("")

                if (extension.enableSizeValidation.get() == true) {
                    dependsOn(validateTaskProvider)
                    sizeStr.set(validateTaskProvider.flatMap { it.size })
                }
                this.sizeStr.set(sizeStr)
                apkDir.set(artifacts)
                token.set(extension.token)
                chatId.set(extension.chatId)

                if (extension.enableContentAnalysis.get() == true) {
                    finalizedBy("analyzeApkContentFor${variant.name.capitalize()}")
                }
            }
        }
    }
}

interface TelegramExtension {
    val chatId: Property<String>
    val token: Property<String>
    val maxApkSize: Property<Int>
    val enableSizeValidation: Property<Boolean>
    val enableContentAnalysis: Property<Boolean>
}
