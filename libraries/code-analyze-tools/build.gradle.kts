plugins {
    id("org.jetbrains.kotlin.jvm")
    id("java-gradle-plugin")
//    `java-gradle-plugin`
//    `kotlin-dsl`
//    kotlin("jvm") version "1.6.10"
}

group = "com.developersancho.codeanalyzetools"

gradlePlugin {
    plugins {
        register("AndroidCoveragePlugin") {
            id = "AndroidCoveragePlugin"
            implementationClass = "com.developersancho.codeanalyzetools.AndroidCoveragePlugin"
        }
        register("VersionUpdatePlugin") {
            id = "version-update-plugin"
            implementationClass = "com.developersancho.codeanalyzetools.VersionUpdatePlugin"
        }
    }
}

dependencies {
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.android.tools.build:gradle:7.1.1")
    //implementation(localGroovy())
    implementation("com.github.ben-manes:gradle-versions-plugin:0.42.0")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.19.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.1.0")
}