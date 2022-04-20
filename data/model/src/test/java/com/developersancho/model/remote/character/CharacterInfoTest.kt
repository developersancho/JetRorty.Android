package com.developersancho.model.remote.character

import com.developersancho.testutils.BaseModelTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class CharacterInfoTest : BaseModelTest() {

    override fun checkModelClass(): Class<*> {
        return CharacterInfo::class.java
    }

    override fun checkModelFields(): List<String> {
        return listOf(
            "created",
            "episode",
            "gender",
            "id",
            "image",
            "location",
            "name",
            "origin",
            "species",
            "status",
            "type",
            "url"
        )
    }

    @Test
    fun createResponse() {
        val characterInfo: CharacterInfo = mockk()
        Assert.assertNotNull(characterInfo)
    }
}