import extensions.*

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("codeanalyzetools.jacoco-report")
}

dependencies {
    FRAMEWORK
    MODEL

    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)

    // Dagger Hilt
    implementation(DaggerHiltLib.Android)
    kapt(DaggerHiltLib.Compiler)
}