import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform") version "1.5.31"
    id("org.jetbrains.compose") version "1.0.0-beta6-dev446"
}

println("Target: ${getTarget()}")

kotlin {
    jvm()
    sourceSets {
        named("commonMain") {
            dependencies {
                api(compose.runtime)
            }
        }
        named("jvmTest") {
            dependencies {
                implementation(compose.uiTestJUnit4)
                //implementation(getSkiaDependency()) // WORKAROUND
                implementation(compose.material)
            }
        }
        named("jvmMain")
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

fun getSkiaDependency() : String {
    val target = getTarget()
    val version = "0.5.9"
    return "org.jetbrains.skiko:skiko-jvm-runtime-$target:$version"
}

fun getTarget(): String {
    val osName = System.getProperty("os.name")
    val targetOs = when {
        osName == "Mac OS X" -> "macos"
        osName.startsWith("Win") -> "windows"
        osName.startsWith("Linux") -> "linux"
        else -> error("Unsupported OS: $osName")
    }

    val targetArch = when (val osArch = System.getProperty("os.arch")) {
        "x86_64", "amd64" -> "x64"
        "aarch64" -> "arm64"
        else -> error("Unsupported arch: $osArch")
    }

    return "${targetOs}-${targetArch}"
}