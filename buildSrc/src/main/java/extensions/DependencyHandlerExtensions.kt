package extensions

import Deps
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler


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

fun DependencyHandler.addComposeDependencies() {
    implementation(Deps.Compose.Ui)
    implementation(Deps.Compose.Runtime)
    implementation(Deps.Compose.Material)
    implementation(Deps.Compose.Preview)
    implementation(Deps.Compose.Activity)
    implementation(Deps.Compose.Foundation)
    implementation(Deps.Compose.MaterialIconCore)
    implementation(Deps.Compose.MaterialIconExtended)
    implementation(Deps.Compose.Coil)
    implementation(Deps.Compose.Paging)
    implementation(Deps.Compose.Constraintlayout)

    androidTestImplementation(Deps.Compose.Junit4)
    debugImplementation(Deps.Compose.DebugTooling)

    implementation(Deps.Accompanist.Insets)
    implementation(Deps.Accompanist.Navigation)
    implementation(Deps.Accompanist.Systemuicontroller)
    implementation(Deps.Accompanist.Swiperefresh)
    implementation(Deps.Accompanist.Permissions)
    implementation(Deps.Accompanist.Placeholder)
    implementation(Deps.Accompanist.Flowlayout)
}

fun DependencyHandler.addCommonDependencies() {
    implementation(Deps.Compose.Ui)
    implementation(Deps.Compose.Material)
    implementation(Deps.Compose.Preview)
    implementation(Deps.Compose.Activity)
    implementation(Deps.Compose.Foundation)
    implementation(Deps.Compose.MaterialIconCore)
    implementation(Deps.Compose.MaterialIconExtended)
    implementation(Deps.Compose.Coil)
    implementation(Deps.Compose.Paging)
    implementation(Deps.Compose.Constraintlayout)

    androidTestImplementation(Deps.Compose.Junit4)
    debugImplementation(Deps.Compose.DebugTooling)

    implementation(Deps.Accompanist.Insets)
    implementation(Deps.Accompanist.Navigation)
    implementation(Deps.Accompanist.Systemuicontroller)
    implementation(Deps.Accompanist.Swiperefresh)
    implementation(Deps.Accompanist.Permissions)
    implementation(Deps.Accompanist.Placeholder)
    implementation(Deps.Accompanist.Flowlayout)
}