/*
 * Copyright (C) 2022, developersancho
 * All rights reserved.
 */
package com.developersancho.model.remote.episode

import com.developersancho.testing.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class EpisodeInfoTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return EpisodeInfo::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "air_date",
            "characters",
            "created",
            "episode",
            "id",
            "name",
            "url"
        )
    }

    @Test
    fun createResponse() {
        val episodeInfo: EpisodeInfo = mockk()
        Assert.assertNotNull(episodeInfo)
    }
}