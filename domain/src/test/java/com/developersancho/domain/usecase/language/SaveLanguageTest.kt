package com.developersancho.domain.usecase.language

import com.developersancho.repository.langauge.LanguageRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveLanguageTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LanguageRepository

    @SpyK
    @InjectMockKs
    private lateinit var saveLanguage: SaveLanguage

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val params = SaveLanguage.Params("tr")

        // Act (When)
        saveLanguage.invoke(params)

        // Assert (Then)
        coVerify { saveLanguage.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val params = SaveLanguage.Params("tr")

        // Act (When)
        saveLanguage.invoke(params).single()

        // Assert (Then)
        coVerify { repository.setLanguage(params.language) }
    }
}