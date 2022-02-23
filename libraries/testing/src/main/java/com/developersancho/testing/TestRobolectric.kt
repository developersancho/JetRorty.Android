/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.testing

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = "AndroidManifest.xml",
    application = TestRobolectric.ApplicationStub::class,
    sdk = [Build.VERSION_CODES.M]
)
open class TestRobolectric : BaseMockTest() {

    protected val application: Application by lazy {
        ApplicationProvider.getApplicationContext<ApplicationStub>()
    }
    protected val context: Context by lazy {
        application
    }

    internal class ApplicationStub : Application()
}