package dev.dariakhartova.todoapp.tasks

import TelegramApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.FileWriter
import java.util.zip.ZipFile
import javax.inject.Inject

abstract class AnalyzeApkContentTask @Inject constructor(
    private val telegramApi: TelegramApi
) : DefaultTask() {

    @get:InputDirectory
    abstract val projectDir: DirectoryProperty

    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:Input
    abstract val token: Property<String>

    @get:Input
    abstract val chatId: Property<String>

    @TaskAction
    fun execute() = runBlocking {
        val token = token.get()
        val chatId = chatId.get()

        val apkFile = apkDir.get().asFile.listFiles()?.first { it.name.endsWith(".apk") }!!
        val zipFile = ZipFile(apkFile)
        val entries = zipFile.entries()
        val details = mutableListOf<String>()

        entries.asSequence().sortedByDescending { it.size }.forEach { entry ->
            val sizeInBytes = entry.size
            val formattedSize = if (sizeInBytes < 1024 * 1024) {
                "${"%.2f".format(sizeInBytes.toDouble() / 1024)} KB"
            } else {
                "${"%.2f".format(sizeInBytes.toDouble() / (1024 * 1024))} MB"
            }
            details.add("- ${entry.name} $formattedSize")
        }

        zipFile.close()

        val projectDir = projectDir.get().asFile
        val outputPath = "${projectDir}/../build-logic/apk-analysis-report-.txt"
        val reportFile = File(outputPath)
        FileWriter(reportFile).use { writer ->
            writer.write("APK Contents:\n")
            details.forEach { writer.write("$it\n") }
        }

        telegramApi.sendFile(reportFile, outputPath, token, chatId)

        reportFile.delete()
    }

}