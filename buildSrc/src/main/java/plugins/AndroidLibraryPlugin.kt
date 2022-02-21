package plugins

/*
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType


// todo: SampleMVVM buildSrc
val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("$name is not an android module")

val Project.library: BaseExtension
    get() = extensions.getByType(LibraryExtension::class)


class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {

    }

    private fun Project.applyPlugins() {
//        plugins.apply(GradlePlugins.AndroidLibrary)
//        plugins.apply(GradlePlugins.KotlinAndroid)
//        plugins.apply(GradlePlugins.KotlinKapt)
//        plugins.apply(GradlePlugins.KotlinParcelize)
    }

    //private fun Project.configureAndroid() = this.extensions.getByType(LibraryExtension::class).run {
//        compileSdk = Configs.CompileSdk
//        defaultConfig.apply {
//            minSdk = Configs.MinSdk
//            targetSdk = Configs.TargetSdk
//            versionCode = Configs.VersionCode
//            versionName = Configs.VersionName
//            multiDexEnabled = true
//            vectorDrawables.useSupportLibrary = true
//            testInstrumentationRunner = Configs.AndroidJunitRunner
//            consumerProguardFiles("consumer-rules.pro")
//        }
//
//        compileOptions.apply {
//            sourceCompatibility = JavaVersion.VERSION_11
//            targetCompatibility = JavaVersion.VERSION_11
//        }
//
//        project.tasks.withType<KotlinCompile>().configureEach {
//            kotlinOptions {
//                jvmTarget = JavaVersion.VERSION_11.toString()
//                allWarningsAsErrors = true
//                freeCompilerArgs = Configs.FreeCompilerArgs
//            }
//        }
//
//        packagingOptions.apply {
//            resources {
//                setExcludes(
//                    setOf(
//                        "META-INF/metadata.kotlin_module",
//                        "META-INF/metadata.jvm.kotlin_module",
//                        "META-INF/AL2.0",
//                        "META-INF/LGPL2.1"
//                    )
//                )
//            }
//        }
//
//        viewBinding.isEnabled = true
//
//        buildTypes.apply {
//            getByName("release") {
//                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
//                //buildConfigStringField("BASE_URL", Configs.Release.BaseUrl)
//                //buildConfigStringField("DB_NAME", Configs.Release.DbName)
//            }
//            getByName("debug") {
//                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
//                //buildConfigStringField("BASE_URL", Configs.Debug.BaseUrl)
//                //buildConfigStringField("DB_NAME", Configs.Debug.DbName)
//            }
//        }
    //}

//    private fun BuildType.buildConfigStringField(name: String, value: String) {
//        this.buildConfigField("String", name, "\"$value\"")
//    }
}*/
