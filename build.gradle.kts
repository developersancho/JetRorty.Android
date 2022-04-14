plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.4" apply false
}

apply<codeanalyzeplugins.VersionUpdatePlugin>()

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "7.4"
}

project.afterEvaluate {
    tasks.create("codeAnalyze") {
        dependsOn(
            ":app:ktlintFormat",
            ":domain:ktlintFormat",
            ":data:model:ktlintFormat",
            ":data:remote:ktlintFormat",
            ":data:local:ktlintFormat",
            ":data:repository:ktlintFormat",
            ":app:spotlessApply",
            ":domain:spotlessApply",
            ":data:model:spotlessApply",
            ":data:remote:spotlessApply",
            ":data:local:spotlessApply",
            ":data:repository:spotlessApply",
            ":app:ktlintCheck",
            ":domain:ktlintCheck",
            ":data:model:ktlintCheck",
            ":data:remote:ktlintCheck",
            ":data:local:ktlintCheck",
            ":data:repository:ktlintCheck",
            ":app:spotlessCheck",
            ":domain:spotlessCheck",
            ":data:model:spotlessCheck",
            ":data:remote:spotlessCheck",
            ":data:local:spotlessCheck",
            ":data:repository:spotlessCheck",
            ":app:detekt",
            ":domain:detekt",
            ":data:model:detekt",
            ":data:remote:detekt",
            ":data:local:detekt",
            ":data:repository:detekt"
        )
    }
}
//
//tasks.create("codeAnalyze") {
//    setDependsOn(
//        listOf(
//            tasks.getByName(":app:ktlintFormat"),
//            tasks.getByName(":domain:ktlintFormat"),
//            tasks.getByName(":data:model:ktlintFormat"),
//            tasks.getByName(":data:remote:ktlintFormat"),
//            tasks.getByName(":data:local:ktlintFormat"),
//            tasks.getByName(":data:repository:ktlintFormat"),
//
//            tasks.getByName(":app:spotlessApply"),
//            tasks.getByName(":domain:spotlessApply"),
//            tasks.getByName(":data:model:spotlessApply"),
//            tasks.getByName(":data:remote:spotlessApply"),
//            tasks.getByName(":data:local:spotlessApply"),
//            tasks.getByName(":data:repository:spotlessApply"),
//
//            tasks.getByName(":app:ktlintCheck"),
//            tasks.getByName(":domain:ktlintCheck"),
//            tasks.getByName(":data:model:ktlintCheck"),
//            tasks.getByName(":data:remote:ktlintCheck"),
//            tasks.getByName(":data:local:ktlintCheck"),
//            tasks.getByName(":data:repository:ktlintCheck"),
//
//            tasks.getByName(":app:spotlessCheck"),
//            tasks.getByName(":domain:spotlessCheck"),
//            tasks.getByName(":data:model:spotlessCheck"),
//            tasks.getByName(":data:remote:spotlessCheck"),
//            tasks.getByName(":data:local:spotlessCheck"),
//            tasks.getByName(":data:repository:spotlessCheck"),
//
//            tasks.getByName(":app:detekt"),
//            tasks.getByName(":domain:detekt"),
//            tasks.getByName(":data:model:detekt"),
//            tasks.getByName(":data:remote:detekt"),
//            tasks.getByName(":data:local:detekt"),
//            tasks.getByName(":data:repository:detekt")
//        )
//    )
//}

// tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
// }