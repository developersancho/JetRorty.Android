pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}
rootProject.name = "JetRorty.Android"
include(":app")
include(":data:model")
include(":data:local")
include(":data:remote")
include(":data:repository")
include(":domain")
include(":common:ui-theme")
include(":common:ui-resource")
include(":common:ui-view")
include(":libraries:framework")
include(":libraries:testing")

