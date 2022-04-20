package com.developersancho.domain.usecase.location

import androidx.paging.PagingConfig
import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetLocationsTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var getLocations: GetLocations

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val pagingConfig = PagingConfig(pageSize = 20)
        val params = GetLocations.Params(pagingConfig, hashMapOf())

        // Act (When)
        getLocations.invoke(params)

        // Assert (Then)
        coVerify { getLocations.invoke(any()) }
    }
}