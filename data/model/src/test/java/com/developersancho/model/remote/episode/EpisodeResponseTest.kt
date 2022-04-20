package com.developersancho.model.remote.episode

import com.developersancho.model.remote.base.PageInfo
import com.developersancho.testutils.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class EpisodeResponseTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return EpisodeResponse::class.java
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
        val episodeInfo: EpisodeInfo = mockk()

        val response = EpisodeResponse(
            pageInfo = pageInfo,
            results = listOf(episodeInfo)
        )

        Assert.assertEquals(episodeInfo, response.results?.first())
        Assert.assertEquals(pageInfo, response.pageInfo)
    }
}