package codeanalyzetools

import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

ktlint {
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