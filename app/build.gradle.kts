import extensions.addComposeDependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
    id("codeanalyzetools.quality")
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

android {
    compileSdk = Configs.CompileSdk

    defaultConfig {
        applicationId = Configs.Id
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        versionCode = Configs.VersionCode
        versionName = Configs.VersionName
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
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
    // implementation(project(Modules.CodeAnalyzeTools))
    implementation(project(Modules.Framework))
    testImplementation(project(Modules.Testing))

    implementation(project(Modules.Theme))
    implementation(project(Modules.Resource))
    implementation(project(Modules.View))
    implementation(project(Modules.Provider))

    implementation(project(Modules.Model))
    implementation(project(Modules.Remote))
    implementation(project(Modules.Local))
    implementation(project(Modules.Repository))
    implementation(project(Modules.Domain))

    implementation(project(Modules.Splash))
    implementation(project(Modules.Characters))
    implementation(project(Modules.Detail))
    implementation(project(Modules.Favorites))
    implementation(project(Modules.Settings))
    implementation(project(Modules.Main))
    implementation(project(Modules.Home))

    addComposeDependencies()

    implementation(Deps.AndroidX.CoreKtx)
    implementation(Deps.AndroidX.LifecycleRuntime)
    implementation(Deps.AndroidX.Paging)
    implementation(Deps.AndroidX.FragmentKtx)
    implementation(Deps.AndroidX.PlayCoreKtx)
    implementation(Deps.AndroidX.Appcompat)
    implementation(Deps.AndroidX.Material)

    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    implementation(Deps.Cache.DatastorePref)
    implementation(Deps.Cache.SecurityPref)
    implementation(Deps.Cache.Room)
    ksp(Deps.Cache.RoomCompiler)

    implementation(Deps.Network.Moshi)
    implementation(Deps.Network.MoshiAdapter)
    ksp(Deps.Network.MoshiCodegen)

    implementation(Deps.Network.Retrofit)
    implementation(Deps.Network.RetrofitMoshi)
    implementation(Deps.Network.Okhttp)
    implementation(Deps.Network.OkhttpInterceptor)
    testImplementation(Deps.Network.OkhttpMock)

    implementation(Deps.AndroidX.CoroutinesAndroid)
    implementation(Deps.AndroidX.CoroutinesCore)

    implementation(Deps.Navigation.Core)
    ksp(Deps.Navigation.Compiler)
    implementation(Deps.Navigation.Animation)

    implementation(Deps.Koin.Android)
    implementation(Deps.Koin.Compose)
    testImplementation(Deps.Koin.Test)

    implementation(Deps.AndroidX.Paging)
    releaseImplementation(Deps.Network.ReleaseChucker)
    debugImplementation(Deps.Network.DebugChucker)

    implementation(Deps.AndroidX.SplashScreen)
    implementation(Deps.Common.Timber)
}