/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.codeanalyzetools

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class VersionUpdatePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins.apply("com.github.ben-manes.versions")
        tasks.named("dependencyUpdates", DependencyUpdatesTask::class.java).configure { task->
            task.rejectVersionIf {
                isNonStable(it.candidate.version)
            }
            task.outputFormatter = "html"
        }
    }

    private fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.uppercase(Locale.getDefault())
                .contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}