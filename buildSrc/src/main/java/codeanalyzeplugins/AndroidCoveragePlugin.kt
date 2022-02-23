package codeanalyzeplugins

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ComponentIdentity
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.*

/**
 * Enables the unit tests coverage in an Android project.
 */
@Suppress("UnstableApiUsage")
class AndroidCoveragePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply("jacoco")

        extensions.configure(JacocoPluginExtension::class.java) {
            toolVersion = JACOCO_VERSION
            reportsDirectory.set(file("$buildDir/coverageReport"))
        }

        withAndroidPlugin {
            fixRobolectricCoverage()
            extensions.getByType(AndroidComponentsExtension::class.java)
                .onVariants { configureCoverageTasks(it) }
        }
    }

    private fun Project.configureCoverageTasks(variant: ComponentIdentity) {
        val testTaskName = "test${variant.name.capitalize(Locale.getDefault())}UnitTest"
        val coverageTaskName = "${testTaskName}Coverage"
        tasks.register(coverageTaskName, JacocoReport::class.java).configure {
            group = COVERAGE_TASKS_GROUP
            description =
                "Calculates the coverage and generates the reports for the variant \"${variant.name}\"."
            reports.apply {
                html.required.set(true)
                xml.required.set(false)
                csv.required.set(false)
            }
            val javaClassDirectories =
                fileTreeOf("$buildDir/intermediates/javac/${variant.name}/classes")
            val kotlinClassDirectories = fileTreeOf("$buildDir/tmp/kotlin-classes/${variant.name}")
            classDirectories.from(javaClassDirectories, kotlinClassDirectories)
            executionData.from("$buildDir/jacoco/$testTaskName.exec")
            variant.sourceSets(project).forEach { sourceSet ->
                sourceDirectories.from(sourceSet)
            }
        }
        tasks.matching { task -> task.name == testTaskName }.configureEach {
            finalizedBy(coverageTaskName)
        }
    }

    private fun AndroidCommonExtension.fixRobolectricCoverage() {
        testOptions.unitTests.all { test ->
            test.extensions.configure(JacocoTaskExtension::class.java) {
                // It must be set to true otherwise the coverage of Robolectric tests is not calculated.
                isIncludeNoLocationClasses = true
                // Required when using JDK 11+.
                excludes = listOf("jdk.internal.*")
            }
        }
    }

    /**
     * Specifies all the files which should be excluded from a coverage report.
     */
    private val COVERAGE_EXCLUSIONS: Set<String> = setOf(
        "**/BuildConfig.*" // AGP
    )

    private fun Project.fileTreeOf(dir: String): FileTree =
        fileTree(mapOf("dir" to dir, "excludes" to COVERAGE_EXCLUSIONS))

    companion object {
        private const val JACOCO_VERSION = "0.8.7"
        private const val COVERAGE_TASKS_GROUP = "Coverage"
    }
}