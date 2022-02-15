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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = Configs.FreeCompilerArgs
    }
}

dependencies {
    implementation(Deps.Network.Moshi)
    implementation(Deps.Network.Retrofit)
    implementation(Deps.Network.RetrofitMoshi)
    implementation(Deps.Network.OkhttpInterceptor)

    api(Deps.Network.OkhttpMock)
    api(Deps.Test.Coroutine)
    api(Deps.Test.Robolectric)
    api(Deps.Test.Assertj)
    api(Deps.Test.TestCore)
    api(Deps.Test.TestRunner)
    api(Deps.Test.TestRules)
    api(Deps.Test.Hamcrest)
    api(Deps.Test.Json)
    api(Deps.Test.Turbine)
    api(Deps.Test.Truth)
    api(Deps.Test.Mockk)
}