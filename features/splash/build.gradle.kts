import extensions.*

plugins {
    id("commons.android-library")
    id("commons.dagger-hilt")
}

dependencies {
    FRAMEWORK
    DOMAIN

    addCommonDependencies()

    implementation(SupportLib.Splashscreen)
    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
}