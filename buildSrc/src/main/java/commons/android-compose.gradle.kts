package commons

import extensions.addComposeDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    addComposeConfig()
}

dependencies {
    // Compose
    addComposeDependencies()
}