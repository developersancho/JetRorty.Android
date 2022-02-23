plugins {
    id("commons.android-library")
    id("commons.android-compose")
}

dependencies {
    implementation(project(Modules.Resource))

    implementation(Deps.AndroidX.CoreKtx)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)
}