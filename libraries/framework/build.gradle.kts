plugins {
    id("commons.android-library")
    id("commons.android-compose")
}

dependencies {
    testImplementation(project(Modules.Testing))

    implementation(Deps.AndroidX.CoreKtx)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.AndroidX.FragmentKtx)
    implementation(Deps.AndroidX.LifecycleRuntime)
    implementation(Deps.AndroidX.Paging)

    implementation(Deps.Network.Moshi)
    implementation(Deps.Network.Retrofit)
    implementation(Deps.Network.Okhttp)

    implementation(Deps.Common.Timber)

    implementation(Deps.AndroidX.CoroutinesAndroid)
    implementation(Deps.AndroidX.CoroutinesCore)

    implementation(Deps.Cache.Room)
    implementation(Deps.Cache.DatastorePref)
    implementation(Deps.Cache.SecurityPref)
}