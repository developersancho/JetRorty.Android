import plugins.AndroidCoreLibraryPlugin
import extensions.*

apply<AndroidCoreLibraryPlugin>()

dependencies {
    testImplementation(project(Modules.Testing))
    implementation(project(Modules.Framework))
    implementation(project(Modules.Model))
    implementation(project(Modules.Remote))
    implementation(project(Modules.Local))

    implementation(Deps.AndroidX.CoreKtx)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.Koin.Android)
    testImplementation(Deps.Koin.Test)
}