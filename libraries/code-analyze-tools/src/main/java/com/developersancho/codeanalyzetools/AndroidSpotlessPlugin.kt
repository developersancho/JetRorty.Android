package com.developersancho.codeanalyzetools
//
//import com.diffplug.gradle.spotless.SpotlessExtension
//import com.diffplug.gradle.spotless.SpotlessPlugin
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.kotlin.dsl.apply
//import org.gradle.kotlin.dsl.configure
//
//class AndroidSpotlessPlugin : Plugin<Project> {
//    override fun apply(project: Project) = with(project) {
//        apply<SpotlessPlugin>()
//        configure<SpotlessExtension> {
//
//            format("xml") {
//                target("**/res/**/*.xml")
//                indentWithSpaces(4)
//                trimTrailingWhitespace()
//            }
//
//            java {
//                target(
//                    fileTree(
//                        mapOf(
//                            "dir" to "src",
//                            "include" to listOf("**/*.kotlin")
//                        )
//                    )
//                )
//                licenseHeaderFile(
//                    file("$rootDir/buildSrc/copyright.kt"),
//                    "^(package|object|import|interface)"
//                )
//                googleJavaFormat().aosp()
//                removeUnusedImports()
//                trimTrailingWhitespace()
//                indentWithSpaces()
//                endWithNewline()
//            }
//
//            kotlin {
//                target(
//                    fileTree(
//                        mapOf(
//                            "dir" to ".",
//                            "include" to listOf("**/*.kt"),
//                            "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
//                        )
//                    )
//                )
//                licenseHeaderFile(
//                    file("$rootDir/buildSrc/copyright.kt"),
//                    "^(package|object|import|interface)"
//                )
//                trimTrailingWhitespace()
//                indentWithSpaces()
//            }
//        }
//    }
//}