object Deps {

    object Version {
        const val Accompanist = "0.22.0-rc"
        const val Compose = "1.1.0-rc03"
        const val ActivityCompose = "1.4.0"
        const val ViewModelCompose = "2.4.0"
        const val LottieCompose = "4.2.2"
        const val ConstraintlayoutCompose = "1.0.0"
        const val PagingCompose = "1.0.0-alpha14"
        const val CoilCompose = "2.0.0-alpha06"
        const val NavigationCompose = "2.4.0"

        const val Raamcosta = "1.2.2-beta"

        const val CoreKtx = "1.7.0"
        const val Coroutines = "1.6.0"
        const val Lifecycle = "2.4.0"
        const val Paging = "3.1.0"
        const val Material = "1.5.0"
        const val FragmentKtx = "1.4.1"
        const val PlayCoreKtx = "1.8.1"
        const val Appcompat = "1.4.1"

        const val Moshi = "1.13.0"
        const val MoshiAdapter = "2.2"
        const val Retrofit = "2.9.0"
        const val Chucker = "3.5.2"
        const val Okhttp = "4.9.3"
        const val Room = "2.4.1"
        const val SecurityPref = "1.1.0-alpha03"
        const val Datastore = "1.0.0"
        const val Koin = "3.1.5"
        const val Timber = "5.0.1"
        const val Robolectric = "4.7.3"
        const val Truth = "1.1.3"
        const val Junit = "4.13.2"
        const val JunitExt = "1.1.3"
        const val Espresso = "3.4.0"
    }

    object Compose {
        const val Ui = "androidx.compose.ui:ui:${Version.Compose}"
        const val Material = "androidx.compose.material:material:${Version.Compose}"
        const val Preview = "androidx.compose.ui:ui-tooling-preview:${Version.Compose}"
        const val DebugTooling = "androidx.compose.ui:ui-tooling:${Version.Compose}"
        const val Junit4 = "androidx.compose.ui:ui-test-junit4:${Version.Compose}"

        const val Foundation = "androidx.compose.foundation:foundation:${Version.Compose}"
        const val MaterialIconCore =
            "androidx.compose.material:material-icons-core:${Version.Compose}"
        const val MaterialIconExtended =
            "androidx.compose.material:material-icons-extended:${Version.Compose}"
        const val Navigation = "androidx.navigation:navigation-compose:${Version.NavigationCompose}"
        const val Activity = "androidx.activity:activity-compose:${Version.ActivityCompose}"
        const val ViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.ViewModelCompose}"
        const val Lottie = "com.airbnb.android:lottie-compose:${Version.LottieCompose}"
        const val Constraintlayout =
            "androidx.constraintlayout:constraintlayout-compose:${Version.ConstraintlayoutCompose}"

        const val Paging = "androidx.paging:paging-compose:${Version.PagingCompose}"
        const val Coil = "io.coil-kt:coil-compose:${Version.CoilCompose}"
    }

    object Accompanist {
        const val Swiperefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Version.Accompanist}"
        const val Permissions =
            "com.google.accompanist:accompanist-permissions:${Version.Accompanist}"
        const val Systemuicontroller =
            "com.google.accompanist:accompanist-systemuicontroller:${Version.Accompanist}"
        const val Insets = "com.google.accompanist:accompanist-insets:${Version.Accompanist}"
        const val Placeholder =
            "com.google.accompanist:accompanist-placeholder-material:${Version.Accompanist}"
        const val Navigation =
            "com.google.accompanist:accompanist-navigation-material:${Version.Accompanist}"
        const val Flowlayout =
            "com.google.accompanist:accompanist-flowlayout:${Version.Accompanist}"
    }

    object AndroidX {
        const val CoreKtx = "androidx.core:core-ktx:${Version.CoreKtx}"
        const val CoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutines}"
        const val CoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutines}"
        const val Paging = "androidx.paging:paging-runtime-ktx:${Version.Paging}"
        const val Material = "com.google.android.material:material:${Version.Material}"

        const val LifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Lifecycle}"

        const val FragmentKtx = "androidx.fragment:fragment-ktx:${Version.FragmentKtx}"
        const val PlayCoreKtx = "com.google.android.play:core-ktx:${Version.PlayCoreKtx}"

        const val Appcompat = "androidx.appcompat:appcompat:${Version.Appcompat}"
    }

    object Network {
        const val Moshi = "com.squareup.moshi:moshi-kotlin:${Version.Moshi}"
        const val MoshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.Moshi}"
        const val MoshiAdapter = "com.serjltt.moshi:moshi-lazy-adapters:${Version.MoshiAdapter}"

        const val Retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
        const val RetrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Version.Retrofit}"

        const val DebugChucker = "com.github.chuckerteam.chucker:library:${Version.Chucker}"
        const val ReleaseChucker = "com.github.chuckerteam.chucker:library-no-op:${Version.Chucker}"

        const val Okhttp = "com.squareup.okhttp3:okhttp:${Version.Okhttp}"
        const val OkhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.Okhttp}"
        const val OkhttpMock = "com.squareup.okhttp3:mockwebserver:${Version.Okhttp}"
    }

    object Cache {
        const val Room = "androidx.room:room-ktx:${Version.Room}"
        const val RoomCompiler = "androidx.room:room-compiler:${Version.Room}"
        const val SecurityPref = "androidx.security:security-crypto-ktx:${Version.SecurityPref}"
        const val Datastore = "androidx.datastore:datastore:${Version.Datastore}"
        const val DatastorePref = "androidx.datastore:datastore-preferences:${Version.Datastore}"
    }

    object Koin {
        const val Android = "io.insert-koin:koin-android:${Version.Koin}"
        const val Compose = "io.insert-koin:koin-androidx-compose:${Version.Koin}"
        const val Test = "io.insert-koin:koin-test:${Version.Koin}"
    }

    object Common {
        const val Timber = "com.jakewharton.timber:timber:${Version.Timber}"
    }

    object Navigation {
        const val Core = "io.github.raamcosta.compose-destinations:core:${Version.Raamcosta}"
        const val Compiler = "io.github.raamcosta.compose-destinations:ksp:${Version.Raamcosta}"
        const val Animation =
            "io.github.raamcosta.compose-destinations:animations-core:${Version.Raamcosta}"
    }

    object Test {
        const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.Coroutines}"
        const val Robolectric = "org.robolectric:robolectric:${Version.Robolectric}"
        const val Truth = "com.google.truth:truth:${Version.Truth}"
        const val Junit = "junit:junit:${Version.Junit}"
        const val JunitExt = "androidx.test.ext:junit:${Version.JunitExt}"
        const val Espresso = "androidx.test.espresso:espresso-core:${Version.Espresso}"
    }
}