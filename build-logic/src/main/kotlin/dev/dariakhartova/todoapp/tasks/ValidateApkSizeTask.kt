package dev.dariakhartova.todoapp.tasks

import TelegramApi
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class ValidateApkSizeTask @Inject constructor(
    private val telegramApi: TelegramApi
) : DefaultTask() {

    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:Input
    abstract val maxApkSize: Property<Int>

    @get:Input
    abstract val token: Property<String>

    @get:Input
    abstract val chatId: Property<String>

    @get:Internal
    val size: Property<String> = project.objects.property(String::class.java)

    @TaskAction
    fun execute() {
        val maxApkSize = maxApkSize.get()
        val token = token.get()
        val chatId = chatId.get()
        var validationFailed = false

        apkDir.get().asFile.listFiles()
            ?.filter { it.name.endsWith(".apk") }
            ?.forEach { apkFile ->
                val fileSizeMb = apkFile.length() / (1024 * 1024)

                if (fileSizeMb > maxApkSize) {
                    validationFailed = true
                    val message =
                        "APK size exceeds the maximum limit of $maxApkSize MB: $fileSizeMb MB (${apkFile.name})"
                    runBlocking {
                        telegramApi.sendMessage(message, token, chatId).apply {
                            println("Status = $status")
                        }
                    }
                }
                size.set("File size: $fileSizeMb MB")
            }

        if (validationFailed) {
            throw GradleException("APK size validation failed")
        }
    }
}
