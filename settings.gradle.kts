pluginManagement {
    // includeBuild("libraries/code-analyze-tools")
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
include(":features:splash")
include(":features:characters")
include(":features:detail")
include(":features:favorites")
include(":features:settings")
include(":features:main")
include(":features:home")
include(":common:provider")
include(":libraries:code-analyze-tools")
// includeBuild("code-analyze-tools")
