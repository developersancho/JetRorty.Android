import extensions.*

plugins {
    id("commons.android-library")
    id("dagger.hilt.android.plugin")
}

dependencies {
    FRAMEWORK
    MODEL
    LOCAL
    REMOTE

    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)

    implementation(StorageLib.DatastorePref)
}