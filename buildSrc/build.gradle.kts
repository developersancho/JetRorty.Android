import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.3"
}

object Versions {
    const val GRADLE = "7.1.1"
    const val KOTLIN = "1.6.10"
    const val KSP = "1.6.10-1.0.2"
    const val VERSION_CHECKER = "0.41.0"
    const val KTLINT = "10.2.0"
    const val SPOTLESS = "6.0.0"
    const val DETEKT = "1.19.0"
}

object Deps {
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val VERSION_CHECKER = "com.github.ben-manes:gradle-versions-plugin:${Versions.VERSION_CHECKER}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.KTLINT}"
    const val SPOTLESS = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.SPOTLESS}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.DETEKT}"
    const val KSP = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Versions.KSP}"
}

dependencies {
//    implementation(gradleApi())
//    implementation(localGroovy())
    implementation(Deps.ANDROID_GRADLE)
    implementation(Deps.KOTLIN_GRADLE)
//    implementation(Deps.VERSION_CHECKER)
//    implementation(Deps.KTLINT)
//    implementation(Deps.SPOTLESS)
//    implementation(Deps.DETEKT)
}