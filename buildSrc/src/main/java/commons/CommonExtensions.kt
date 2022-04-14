package commons

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Adds the base Compose configurations on Gradle.
 */
fun CommonExtension<*, *, *, *>.addComposeConfig() {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose
    }

    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
}

/**
 * Adds the base default app configurations on Gradle.
 */
fun CommonExtension<*, *, *, *>.addDefaultConfig() {
    defaultConfig {
        compileSdk = Configs.CompileSdk
        minSdk = Configs.MinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}