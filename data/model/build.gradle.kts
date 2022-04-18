import extensions.FRAMEWORK
import extensions.implementation
import extensions.ksp

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
    id("codeanalyzetools.jacoco-report")
}

dependencies {
    FRAMEWORK

    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiLazyAdapter)

    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
}