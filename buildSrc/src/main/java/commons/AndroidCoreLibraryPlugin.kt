package commons

import Configs
import com.android.build.api.dsl.BuildType
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidCoreLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugins()
        target.configureAndroid()
        target.configureKotlin()
    }

    private fun Project.applyPlugins() {
        plugins.apply("com.android.library")
        plugins.apply("org.jetbrains.kotlin.android")
        plugins.apply("org.jetbrains.kotlin.plugin.parcelize")
        plugins.apply("com.google.devtools.ksp")
        plugins.apply("org.jetbrains.kotlin.kapt")
    }

    private fun Project.configureKotlin() =
        extensions.getByType(KotlinAndroidProjectExtension::class).run {
            sourceSets.apply {
                getByName("main").kotlin.srcDir("build/generated/ksp/main/kotlin")
                getByName("test").kotlin.srcDir("build/generated/ksp/test/kotlin")
                getByName("debug").kotlin.srcDir("build/generated/ksp/debug/kotlin")
                getByName("release").kotlin.srcDir("build/generated/ksp/release/kotlin")
            }
        }

    private fun Project.configureAndroid() = extensions.getByType(LibraryExtension::class).run {
        compileSdk = Configs.CompileSdk
        defaultConfig.apply {
            minSdk = Configs.MinSdk
            targetSdk = Configs.TargetSdk
            testInstrumentationRunner = Configs.AndroidJunitRunner
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
                freeCompilerArgs = listOf(
                    "-Xjvm-default=all",
                    "-Xopt-in=kotlin.RequiresOptIn",
                    "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
                    "-Xopt-in=kotlinx.coroutines.FlowPreview",
                    "-Xopt-in=kotlin.Experimental"
                )
            }
        }

        buildTypes.apply {
            getByName("release") {
                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                buildConfigStringField("BASE_URL", Configs.Release.BaseUrl)
                buildConfigStringField("DB_NAME", Configs.Release.DbName)
            }
            getByName("debug") {
                proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                buildConfigStringField("BASE_URL", Configs.Debug.BaseUrl)
                buildConfigStringField("DB_NAME", Configs.Debug.DbName)
            }
        }
    }

    private fun BuildType.buildConfigStringField(name: String, value: String) {
        this.buildConfigField("String", name, "\"$value\"")
    }
}