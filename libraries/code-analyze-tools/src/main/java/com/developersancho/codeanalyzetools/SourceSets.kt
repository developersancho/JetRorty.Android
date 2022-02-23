/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.codeanalyzetools

import com.android.build.api.variant.ComponentIdentity
import org.gradle.api.Project
import java.io.File

/**
 * Gets the source sets from the given variant.
 * The source sets are returned as files.
 */
@Suppress("UnstableApiUsage")
internal fun ComponentIdentity.sourceSets(project: Project): Collection<File> {
    val buildType = requireNotNull(buildType) { "The build type is required." }
    val sourceSets = mutableSetOf("main", buildType)
    if (!flavorName.isNullOrBlank()) {
        sourceSets += productFlavors.map { it.second }
        sourceSets += flavorName!!
        sourceSets += name
    }
    return sourceSets.map { project.file("src/$it/java") } +
        sourceSets.map { project.file("src/$it/kotlin") }
}
