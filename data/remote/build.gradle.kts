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

    implementation(Deps.Network.Moshi)
    implementation(Deps.Network.Retrofit)
    implementation(Deps.Network.RetrofitMoshi)
    implementation(Deps.Network.Okhttp)
    implementation(Deps.Network.OkhttpInterceptor)
    testImplementation(Deps.Network.OkhttpMock)

    releaseImplementation(Deps.Network.ReleaseChucker)
    debugImplementation(Deps.Network.DebugChucker)

    implementation(Deps.Koin.Android)
    testImplementation(Deps.Koin.Test)
}