plugins {
    id("commons.android-library")
    id("commons.android-compose")
}

dependencies {
    implementation(project(Modules.Theme))
    implementation(project(Modules.Resource))

    implementation(Deps.AndroidX.CoreKtx)
    implementation(Deps.AndroidX.Appcompat)
    implementation(Deps.AndroidX.Material)

    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)
}