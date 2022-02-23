package codeanalyzeplugins

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class AndroidDetektPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply("io.gitlab.arturbosch.detekt")
        configure<DetektExtension> {
            autoCorrect = true
            toolVersion = "1.19.0"
            parallel = false
            source = files(
                "src/main/kotlin",
                "src/main/java"
            )
            config = files("${project.rootDir}/buildSrc/detekt.yml")
        }

        tasks.withType<Detekt>().configureEach {
            reports {
                html.required.set(true)
                html.outputLocation.set(file("${project.buildDir}/build/reports/detekt/detekt-report.html"))
                xml.required.set(true)
                xml.outputLocation.set(file("${project.buildDir}/build/reports/detekt/detekt-report.xml"))
            }
        }

        tasks.withType<Detekt>().configureEach {
            include("**/*.kt", "**/*.kts")
            exclude("**/build/**", ".*/resources/.*", ".*test.*,.*/resources/.*,.*/tmp/.*")

            jvmTarget = JavaVersion.VERSION_11.toString()
        }

        applyDependencies()
    }

    private fun Project.applyDependencies() {
        dependencies.apply {
            add("detektPlugins", "io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
        }
    }
}