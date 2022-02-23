plugins {
    id("commons.android-library")
    id("codeanalyzetools.quality")
}

dependencies {
    implementation(project(Modules.Resource))
    implementation(project(Modules.Framework))

    implementation(Deps.AndroidX.Appcompat)
    implementation(Deps.AndroidX.ViewModel)
    implementation(Deps.AndroidX.LifecycleRuntime)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.AndroidX.SplashScreen)
    implementation(Deps.AndroidX.CoroutinesAndroid)
    implementation(Deps.AndroidX.CoroutinesCore)
    implementation(Deps.Koin.Android)
}