package codeanalyzeplugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

class AndroidKtlintPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply("org.jlleitschuh.gradle.ktlint")
        configure<KtlintExtension> {
            version.set("0.42.1")
            debug.set(true)
            verbose.set(true)
            android.set(false)
            outputToConsole.set(true)
            outputColorName.set("RED")
            enableExperimentalRules.set(true)
            ignoreFailures.set(false)
            disabledRules.set(
                setOf(
                    "import-ordering",
                    "no-wildcard-imports",
                    "experimental:annotation",
                    "experimental:argument-list-wrapping",
                    "experimental:trailingcomma:trailing-comma",
                    "final-newline"
                )
            )
            reporters {
                reporter(ReporterType.HTML)
                reporter(ReporterType.CHECKSTYLE)
            }
            filter {
                exclude("**/generated/**")
                exclude("**/build/**")
                include("**/kotlin/**")
            }
        }
    }
}