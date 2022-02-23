package codeanalyzetools

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

configure<SpotlessExtension> {

    format("xml") {
        target("**/res/**/*.xml")
        indentWithSpaces(4)
        trimTrailingWhitespace()
    }

    java {
        target(
            fileTree(
                mapOf(
                    "dir" to "src",
                    "include" to listOf("**/*.kotlin")
                )
            )
        )
        licenseHeaderFile(
            file("$rootDir/buildSrc/copyright.kt"),
            "^(package|object|import|interface)"
        )
        googleJavaFormat().aosp()
        removeUnusedImports()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )
        licenseHeaderFile(
            file("$rootDir/buildSrc/copyright.kt"),
            "^(package|object|import|interface)"
        )
        trimTrailingWhitespace()
        indentWithSpaces()
    }
}