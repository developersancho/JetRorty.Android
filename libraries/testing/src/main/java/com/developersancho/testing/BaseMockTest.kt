/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.testing

import org.junit.After
import org.junit.Before
import org.junit.Rule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll

open class BaseMockTest {
    open fun onCreate() {}

    open fun onDestroy() {}

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        onCreate()
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
        onDestroy()
    }
}