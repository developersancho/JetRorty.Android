plugins {
    id("commons.android-library")
    id("commons.android-compose")
    id("codeanalyzetools.quality")
}

dependencies {
    testImplementation(project(Modules.Testing))
    implementation(project(Modules.Resource))
    implementation(project(Modules.Framework))
    implementation(project(Modules.Theme))
    implementation(project(Modules.Resource))
    implementation(project(Modules.View))
    implementation(project(Modules.Provider))

    implementation(project(Modules.Model))
    implementation(project(Modules.Remote))
    implementation(project(Modules.Local))
    implementation(project(Modules.Repository))
    implementation(project(Modules.Domain))

    implementation(Deps.AndroidX.Appcompat)
    implementation(Deps.AndroidX.CoreKtx)
    implementation(Deps.AndroidX.ViewModel)
    implementation(Deps.AndroidX.LifecycleRuntime)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.AndroidX.CoroutinesAndroid)
    implementation(Deps.AndroidX.CoroutinesCore)

    implementation(Deps.Koin.Android)
    implementation(Deps.Koin.Compose)
    testImplementation(Deps.Koin.Test)
}