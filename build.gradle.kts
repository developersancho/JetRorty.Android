buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("com.huawei.agconnect:agcp:1.6.5.300")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.6.10-1.0.4" apply false
}

apply<codequality.DependencyUpdatePlugin>()

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}