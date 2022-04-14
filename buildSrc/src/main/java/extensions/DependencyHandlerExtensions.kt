package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

/**
 * Adds a dependency to the `releaseImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.releaseImplementation(dependencyNotation: Any): Dependency? =
    add("releaseImplementation", dependencyNotation)

/**
 * Adds a dependency to the `debugImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

/**
 * Adds a dependency to the `testImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)


/**
 * Adds a dependency to the `androidTestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

/**
 * Adds a dependency to the `ksp` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.ksp(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

fun DependencyHandler.addCommonDependencies() {
    implementation(SupportLib.CoreKtx)
    implementation(SupportLib.Appcompat)
    implementation(SupportLib.Material)
    implementation(SupportLib.CoroutineCore)
    implementation(SupportLib.CoroutineAndroid)
    implementation(SupportLib.LifecycleRuntime)
    implementation(SupportLib.ActivityKtx)
}

fun DependencyHandler.addComposeDependencies() {
    implementation(ComposeLib.Ui)
    implementation(ComposeLib.Material)
    implementation(ComposeLib.Preview)
    implementation(ComposeLib.Runtime)
    implementation(ComposeLib.Foundation)
    implementation(ComposeLib.MaterialIconCore)
    implementation(ComposeLib.MaterialIconExtended)
    androidTestImplementation(AndroidTestingLib.ComposeTestJunit)
    debugImplementation(ComposeLib.Tooling)
    debugImplementation(ComposeLib.Manifest)
    //
    implementation(ComposeLib.Activity)
    implementation(ComposeLib.ViewModel)
    implementation(ComposeLib.ConstraintLayout)
    implementation(ComposeLib.Lottie)
    implementation(ComposeLib.Paging)
    implementation(ComposeLib.Coil)

    // Accompanist
    implementation(AccompanistLib.Swiperefresh)
    implementation(AccompanistLib.Systemuicontroller)
    implementation(AccompanistLib.Insets)
    implementation(AccompanistLib.PlaceholderMaterial)
    implementation(AccompanistLib.NavigationMaterial)
    implementation(AccompanistLib.Permissions)
    implementation(AccompanistLib.Pager)
    implementation(AccompanistLib.Indicators)
    implementation(AccompanistLib.Webview)
}

fun DependencyHandler.addNavigationDependencies() {
    // Navigation
    implementation(NavigationLib.Navigation)
    implementation(NavigationLib.DestinationCore)
    ksp(NavigationLib.DestinationKsp)
    implementation(NavigationLib.DestinationAnimation)
}

fun DependencyHandler.addNetworkDependencies() {
    // Network
    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiLazyAdapter)
    implementation(NetworkLib.Retrofit)
    implementation(NetworkLib.RetrofitMoshi)
    implementation(NetworkLib.Okhttp)
    implementation(NetworkLib.LoggingInterceptor)
    testImplementation(TestingLib.Okhttp)
    debugImplementation(NetworkLib.ChuckerDebug)
    releaseImplementation(NetworkLib.ChuckerRelease)
}

fun DependencyHandler.addStorageDependencies() {
    // Storage
    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
    implementation(StorageLib.DatastorePref)
    implementation(StorageLib.Datastore)
    implementation(StorageLib.SecurityPref)
}

fun DependencyHandler.addFirebaseDependencies() {
    // Firebase
    implementation(FirebaseLib.Base)
    implementation(FirebaseLib.Analytic)
    implementation(FirebaseLib.Crashlytics)
    implementation(FirebaseLib.Push)
    implementation(FirebaseLib.Config)
}

fun DependencyHandler.addHuaweiDependencies() {
    // Huawei
    implementation(HuaweiLib.Base)
    implementation(HuaweiLib.Core)
    implementation(HuaweiLib.Analytic)
    implementation(HuaweiLib.Crashlytics)
    implementation(HuaweiLib.Push)
    implementation(HuaweiLib.Config)
}

fun DependencyHandler.addTestDependencies() {
    // Test
    testImplementation(TestingLib.Junit)
    androidTestImplementation(AndroidTestingLib.JunitExt)
    androidTestImplementation(AndroidTestingLib.EspressoCore)
    testImplementation(TestingLib.Coroutine)
    testImplementation(TestingLib.Truth)
    testImplementation(TestingLib.Robolectric)
    testImplementation(TestingLib.Turbine)
    testImplementation(TestingLib.Mockk)
}

fun DependencyHandler.addModuleDependencies() {
    // Modules
    implementation(project(mapOf("path" to ":data:model")))
    implementation(project(mapOf("path" to ":data:local")))
    implementation(project(mapOf("path" to ":data:remote")))
    implementation(project(mapOf("path" to ":data:repository")))
    implementation(project(mapOf("path" to ":domain")))

    implementation(project(mapOf("path" to ":common:provider")))
    implementation(project(mapOf("path" to ":common:theme")))
    implementation(project(mapOf("path" to ":common:component")))

    implementation(project(mapOf("path" to ":libraries:framework")))
    implementation(project(mapOf("path" to ":libraries:jetframework")))

    implementation(project(mapOf("path" to ":features:welcome")))
    implementation(project(mapOf("path" to ":features:locations")))
    implementation(project(mapOf("path" to ":features:episodes")))
    implementation(project(mapOf("path" to ":features:settings")))
    implementation(project(mapOf("path" to ":features:characters")))
    implementation(project(mapOf("path" to ":features:splash")))
    implementation(project(mapOf("path" to ":features:home")))
}

// Modules
val DependencyHandler.FEATURE_WELCOME
    get() = implementation(project(mapOf("path" to ":features:welcome")))

val DependencyHandler.FEATURE_LOCATIONS
    get() = implementation(project(mapOf("path" to ":features:locations")))

val DependencyHandler.FEATURE_EPISODES
    get() = implementation(project(mapOf("path" to ":features:episodes")))

val DependencyHandler.FEATURE_SETTINGS
    get() = implementation(project(mapOf("path" to ":features:settings")))

val DependencyHandler.FEATURE_CHARACTERS
    get() = implementation(project(mapOf("path" to ":features:characters")))

val DependencyHandler.FEATURE_HOME
    get() = implementation(project(mapOf("path" to ":features:home")))

val DependencyHandler.FEATURE_MAIN
    get() = implementation(project(mapOf("path" to ":features:main")))

val DependencyHandler.FEATURE_SPLASH
    get() = implementation(project(mapOf("path" to ":features:splash")))


val DependencyHandler.MODEL
    get() = implementation(project(mapOf("path" to ":data:model")))

val DependencyHandler.LOCAL
    get() = implementation(project(mapOf("path" to ":data:local")))

val DependencyHandler.REMOTE
    get() = implementation(project(mapOf("path" to ":data:remote")))

val DependencyHandler.REPOSITORY
    get() = implementation(project(mapOf("path" to ":data:repository")))

val DependencyHandler.DOMAIN
    get() = implementation(project(mapOf("path" to ":domain")))

val DependencyHandler.PROVIDER
    get() = implementation(project(mapOf("path" to ":common:provider")))

val DependencyHandler.THEME
    get() = implementation(project(mapOf("path" to ":common:theme")))

val DependencyHandler.COMPONENT
    get() = implementation(project(mapOf("path" to ":common:component")))

val DependencyHandler.FRAMEWORK
    get() = implementation(project(mapOf("path" to ":libraries:framework")))

val DependencyHandler.JETFRAMEWORK
    get() = implementation(project(mapOf("path" to ":libraries:jetframework")))