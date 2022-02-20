plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
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
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("String", "DB_NAME", "\"RortyDb\"")
        }

        debug {
            buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
            buildConfigField("String", "DB_NAME", "\"RortyDb\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = Configs.FreeCompilerArgs
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

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
        main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
        }
        test {
            kotlin.srcDir("build/generated/ksp/test/kotlin")
        }
    }
}

ksp {
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "main")
}

dependencies {
    implementation(project(Modules.Resource))
    implementation(project(Modules.Framework))
    implementation(project(Modules.Theme))
    implementation(project(Modules.Resource))
    implementation(project(Modules.View))
    implementation(project(Modules.Provider))

    implementation(project(Modules.Home))
    implementation(project(Modules.Detail))

    implementation(Deps.AndroidX.Appcompat)
    implementation(Deps.AndroidX.CoreKtx)
    implementation(Deps.AndroidX.ViewModel)
    implementation(Deps.AndroidX.LifecycleRuntime)
    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.AndroidX.CoroutinesAndroid)
    implementation(Deps.AndroidX.CoroutinesCore)

    implementation(Deps.Koin.Android)
    implementation(Deps.Koin.Compose)
    testImplementation(Deps.Koin.Test)

    implementation(Deps.Compose.Ui)
    implementation(Deps.Compose.Material)
    implementation(Deps.Compose.Preview)
    implementation(Deps.Compose.Activity)
    implementation(Deps.Compose.Foundation)
    implementation(Deps.Compose.MaterialIconCore)
    implementation(Deps.Compose.MaterialIconExtended)

    implementation(Deps.Accompanist.Insets)
    implementation(Deps.Accompanist.Navigation)
    implementation(Deps.Accompanist.Systemuicontroller)
    implementation(Deps.Accompanist.Swiperefresh)

    implementation(Deps.Navigation.Core)
    ksp(Deps.Navigation.Compiler)
    implementation(Deps.Navigation.Animation)
}