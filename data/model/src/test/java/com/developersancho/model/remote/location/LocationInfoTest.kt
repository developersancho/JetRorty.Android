package com.developersancho.model.remote.location

import com.developersancho.testing.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class LocationInfoTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return LocationInfo::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "dimension",
            "residents",
            "created",
            "type",
            "id",
            "name",
            "url"
        )
    }

    @Test
    fun createResponse() {
        val locationInfo: LocationInfo = mockk()
        Assert.assertNotNull(locationInfo)
    }
}