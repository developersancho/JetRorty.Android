package codeanalyzetools

plugins {
    id("codeanalyzetools.detekt-check")
    id("codeanalyzetools.ktlint-check")
}

tasks.getByName("check") {
    setDependsOn(
        listOf(
            tasks.getByName("ktlintFormat"),
            tasks.getByName("ktlintCheck"),
            tasks.getByName("detekt")
        )
    )
}

val codeAnalyze by tasks.registering {
    setDependsOn(
        listOf(
            tasks.getByName("ktlintFormat"),
            tasks.getByName("ktlintCheck"),
            tasks.getByName("detekt")
        )
    )
}
