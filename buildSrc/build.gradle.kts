plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("DependencyUpdatePlugin") {
            id = "dependency.update.plugin"
            implementationClass = "codequality.DependencyUpdatePlugin"
        }
        register("AndroidCoreLibraryPlugin") {
            id = "android.core.library.plugin"
            implementationClass = "commons.AndroidCoreLibraryPlugin"
        }
    }
}

object Versions {
    const val GRADLE = "7.1.3"
    const val KOTLIN = "1.6.10"
    const val HILT = "2.41"
    const val GOOGLE_SERVICE = "4.3.10"
    const val CRASHLYTICS = "2.8.1"
    const val VERSION_CHECKER = "0.42.0"
    const val KTLINT = "10.2.1"
    const val SPOTLESS = "6.4.2"
    const val DETEKT = "1.20.0-RC2"
}

object Deps {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
    const val GOOGLE_SERVICE = "com.google.gms:google-services:${Versions.GOOGLE_SERVICE}"
    const val CRASHLYTICS = "com.google.firebase:firebase-crashlytics-gradle:${Versions.CRASHLYTICS}"
    const val VERSION_CHECKER =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.VERSION_CHECKER}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.KTLINT}"
    const val SPOTLESS = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.SPOTLESS}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.DETEKT}"
}

dependencies {
    implementation(Deps.ANDROID_GRADLE)
    implementation(Deps.KOTLIN_GRADLE)
    implementation(Deps.HILT)
    implementation(Deps.GOOGLE_SERVICE)
    implementation(Deps.CRASHLYTICS)
    implementation(Deps.VERSION_CHECKER)
    implementation(Deps.KTLINT)
    implementation(Deps.SPOTLESS)
    implementation(Deps.DETEKT)
}