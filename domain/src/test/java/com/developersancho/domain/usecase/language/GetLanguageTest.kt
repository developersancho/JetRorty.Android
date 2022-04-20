package com.developersancho.domain.usecase.language

import com.developersancho.repository.langauge.LanguageRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetLanguageTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LanguageRepository

    @SpyK
    @InjectMockKs
    private lateinit var getLanguage: GetLanguage

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getLanguage.invoke(Unit)

        // Assert (Then)
        coVerify { repository.getLanguage }
    }
}