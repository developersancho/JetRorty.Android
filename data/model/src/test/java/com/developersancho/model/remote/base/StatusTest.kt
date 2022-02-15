package com.developersancho.model.remote.base

import org.junit.Assert
import org.junit.Test

class StatusTest {

    @Test
    fun checkFieldValues() {
        Assert.assertEquals(Status.Alive.value, "Alive")
        Assert.assertEquals(Status.Dead.value, "Dead")
        Assert.assertEquals(Status.Unknown.value, "unknown")
    }
}