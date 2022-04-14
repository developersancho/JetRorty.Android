pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
        maven("https://developer.huawei.com/repo/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
        maven("https://developer.huawei.com/repo/")
    }
}
rootProject.name = "JetRortyV2"
include(":app")
include(":domain")
include(":data:model")
include(":data:local")
include(":data:remote")
include(":data:repository")
include(":common:provider")
include(":common:component")
include(":common:theme")
include(":libraries:framework")
include(":libraries:jetframework")
include(":features:characters")
include(":features:episodes")
include(":features:locations")
include(":features:splash")
include(":features:welcome")
include(":features:settings")
include(":features:home")
