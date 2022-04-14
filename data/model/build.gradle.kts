import extensions.implementation
import extensions.ksp

plugins {
    id("commons.android-library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(mapOf("path" to ":libraries:framework")))

    implementation(NetworkLib.Moshi)
    ksp(NetworkLib.MoshiCodegen)
    implementation(NetworkLib.MoshiLazyAdapter)

    implementation(StorageLib.RoomKtx)
    ksp(StorageLib.RoomCompiler)
}