package convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.configure


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

configure<LibraryExtension> {
    namespace = "dev.dariakhartova.feature"
    compileSdk = 34
}



internal val Project.libs: LibrariesForLibs
    get() = (this as ExtensionAware).extensions.getByName("libs") as LibrariesForLibs
