plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Configs.CompileSdk

    defaultConfig {
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {

        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Version.Compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
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

    implementation(Deps.Compose.Ui)
    implementation(Deps.Compose.Material)
    implementation(Deps.Compose.Preview)

    implementation(Deps.Compose.Foundation)
    implementation(Deps.Compose.MaterialIconCore)
    implementation(Deps.Compose.MaterialIconExtended)
    implementation(Deps.Compose.Coil)

    implementation(Deps.Accompanist.Insets)
    implementation(Deps.Accompanist.Navigation)
    implementation(Deps.Accompanist.Systemuicontroller)
    implementation(Deps.Accompanist.Swiperefresh)
    implementation(Deps.Accompanist.Placeholder)
}