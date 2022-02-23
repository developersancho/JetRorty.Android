/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.codeanalyzetools

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import com.android.build.gradle.LibraryExtension as LegacyLibraryExtension

@Suppress("UnstableApiUsage")
internal typealias AndroidCommonExtension = CommonExtension<*, *, *, *>

@Suppress("UnstableApiUsage")
internal typealias AndroidApplicationExtension = ApplicationExtension

@Suppress("UnstableApiUsage")
internal typealias AndroidLibraryExtension = LibraryExtension

/**
 * Executes the given action when an Android plugin is applied using the new API.
 */
@Suppress("UnstableApiUsage")
internal inline fun Project.withAndroidPlugin(crossinline config: AndroidCommonExtension.() -> Unit) {
    withAndroidApplicationPlugin(config)
    withAndroidLibraryPlugin(config)
}

/**
 * Executes the given action when an Android application plugin is applied using the new API.
 */
@Suppress("UnstableApiUsage")
internal inline fun Project.withAndroidApplicationPlugin(crossinline config: AndroidApplicationExtension.() -> Unit) {
    pluginManager.withPlugin("com.android.application") { androidPluginExtension<AndroidApplicationExtension>().config() }
}

/**
 * Executes the given action when an Android library plugin is applied using the new API.
 */
@Suppress("UnstableApiUsage")
internal inline fun Project.withAndroidLibraryPlugin(crossinline config: AndroidLibraryExtension.() -> Unit) {
    pluginManager.withPlugin("com.android.library") { androidPluginExtension<AndroidLibraryExtension>().config() }
}

/**
 * Executes the given action when an Android library plugin is applied using the old API.
 */
internal inline fun Project.withAndroidLibraryLegacyPlugin(crossinline config: LegacyLibraryExtension.() -> Unit) {
    pluginManager.withPlugin("com.android.library") { extensions.getByType(LegacyLibraryExtension::class.java).config() }
}

@Suppress("UnstableApiUsage")
private inline fun <reified T : AndroidCommonExtension> Project.androidPluginExtension() = extensions.getByType(T::class.java)
