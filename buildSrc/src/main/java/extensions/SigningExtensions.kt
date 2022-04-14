package extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

fun BaseExtension.setSigningConfigs(project: Project) = signingConfigs {
    create("signingConfigRelease") {
        val keystorePropertiesFile = project.rootProject.file("signing/release.signing.properties")
        if (!keystorePropertiesFile.exists()) {
            System.err.println("📜 Missing release.signing.properties file for release signing")
        } else {
            val keystoreProperties = Properties().apply {
                load(FileInputStream(keystorePropertiesFile))
            }
            try {
                storeFile =
                    project.rootProject.file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            } catch (e: Exception) {
                System.err.println("📜 release.signing.properties file is malformed")
            }
        }
    }

    getByName("debug") {
        storeFile = project.rootProject.file("signing/debug.keystore")
        keyAlias = "androiddebugkey"
        keyPassword = "android"
        storePassword = "android"
    }
}