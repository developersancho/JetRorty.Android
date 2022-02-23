import plugins.AndroidCoreLibraryPlugin
import extensions.*

apply<AndroidCoreLibraryPlugin>()

dependencies {
    testImplementation(project(Modules.Testing))
    implementation(project(Modules.Model))
    implementation(project(Modules.Framework))

    implementation(Deps.AndroidX.CoreKtx)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.Cache.Room)
    ksp(Deps.Cache.RoomCompiler)

    implementation(Deps.Koin.Android)
    testImplementation(Deps.Koin.Test)
}