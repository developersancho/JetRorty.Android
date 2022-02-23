import plugins.AndroidCoreLibraryPlugin
import extensions.*

apply<AndroidCoreLibraryPlugin>()

dependencies {
    implementation(project(Modules.Framework))
    testImplementation(project(Modules.Testing))

    implementation(Deps.AndroidX.CoreKtx)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.Cache.Room)
    ksp(Deps.Cache.RoomCompiler)

    implementation(Deps.Network.Moshi)
    implementation(Deps.Network.MoshiAdapter)
    ksp(Deps.Network.MoshiCodegen)
}