package com.developersancho.model.remote.location

import com.developersancho.model.remote.base.PageInfo
import com.developersancho.testutils.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class LocationResponseTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return LocationResponse::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "info",
            "results"
        )
    }

    @Test
    fun createResponse() {
        val pageInfo: PageInfo = mockk()
        val locationInfo: LocationInfo = mockk()

        val response = LocationResponse(
            pageInfo = pageInfo,
            results = listOf(locationInfo)
        )

        Assert.assertEquals(locationInfo, response.results?.first())
        Assert.assertEquals(pageInfo, response.pageInfo)
    }
}