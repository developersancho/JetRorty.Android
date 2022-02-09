plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.devtools.ksp")
}

kotlin {
    sourceSets.debug {
        kotlin.srcDir("build/generated/ksp/debug/kotlin")
    }
    sourceSets.release {
        kotlin.srcDir("build/generated/ksp/release/kotlin")
    }
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

/*android.applicationVariants.all { variant ->
    kotlin.sourceSets {
        def name = variant.name
        getByName(name) {
            kotlin.srcDir("build/generated/ksp/$name/kotlin")
        }
    }
}*/

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.developersancho.jetrorty"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.0"
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
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview",
            "-Xopt-in=kotlin.Experimental",
            "-Xjvm-default=all",
            "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-Xopt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
        )
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

    implementation(project(Modules.Framework))
    testImplementation(project(Modules.Testing))

    implementation(project(Modules.Theme))
    implementation(project(Modules.Resource))
    implementation(project(Modules.View))

    implementation(project(Modules.Model))
    implementation(project(Modules.Remote))
    implementation(project(Modules.Local))
    implementation(project(Modules.Repository))
    implementation(project(Modules.Domain))

    implementation(Deps.AndroidX.CoreKtx)
    implementation(Deps.Compose.Ui)
    implementation(Deps.Compose.Material)
    implementation(Deps.Compose.Preview)
    implementation(Deps.AndroidX.LifecycleRuntime)
    implementation(Deps.Compose.Activity)
    implementation(Deps.Compose.Foundation)
    implementation(Deps.Compose.MaterialIconCore)
    implementation(Deps.Compose.MaterialIconExtended)
    implementation(Deps.Compose.Coil)
    implementation(Deps.Compose.Paging)
    implementation(Deps.Compose.Constraintlayout)

    implementation(Deps.AndroidX.Paging)

    implementation(Deps.AndroidX.FragmentKtx)
    implementation(Deps.AndroidX.PlayCoreKtx)

    implementation(Deps.AndroidX.Appcompat)
    implementation(Deps.AndroidX.Material)

    testImplementation(Deps.Test.Junit)
    androidTestImplementation(Deps.Test.JunitExt)
    androidTestImplementation(Deps.Test.Espresso)

    androidTestImplementation(Deps.Compose.Junit4)
    debugImplementation(Deps.Compose.DebugTooling)

    implementation(Deps.Accompanist.Insets)
    implementation(Deps.Accompanist.Navigation)
    implementation(Deps.Accompanist.Systemuicontroller)
    implementation(Deps.Accompanist.Swiperefresh)

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
}