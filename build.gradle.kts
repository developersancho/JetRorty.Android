plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id("com.google.devtools.ksp") version "1.6.10-1.0.2" apply false
    //id("com.github.ben-manes.versions").version("0.41.0")
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
    gradleVersion = "7.4"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

/**
 *  commented for spotless plugin
tasks.register("clean", Delete::class) {
delete(rootProject.buildDir)
}
 */