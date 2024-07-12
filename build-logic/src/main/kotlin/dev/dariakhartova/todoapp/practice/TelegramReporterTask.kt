package dev.dariakhartova.todoapp.practice

import TelegramApi
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class TelegramReporterTask @Inject constructor(
    private val telegramApi: TelegramApi
) : DefaultTask() {

    @get:InputDirectory
    abstract val apkDir: DirectoryProperty

    @get:Input
    abstract val token: Property<String>

    @get:Input
    abstract val chatId: Property<String>

    @get:Input
    abstract val sizeStr: Property<String>

    @TaskAction
    fun execute() {
        val token = token.get()
        val chatId = chatId.get()
        val sizeStr = sizeStr.get()

        apkDir.get().asFile.listFiles()
            ?.filter { it.name.endsWith(".apk") }
            ?.forEach {
                runBlocking {
                    telegramApi.sendMessage("Build Success", token, chatId).apply {
                        println("Status = $status")
                    }
                }
                runBlocking {
                    if (sizeStr.isNotEmpty()) {
                        telegramApi.sendMessage(message = sizeStr, token = token, chatId = chatId)
                    }
                }

                runBlocking {
                    telegramApi.upload(it, token, chatId).apply {
                        println("Status = $status")
                    }
                }
            }
    }
}