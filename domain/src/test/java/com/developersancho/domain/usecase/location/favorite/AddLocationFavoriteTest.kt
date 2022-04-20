package com.developersancho.domain.usecase.location.favorite

import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.dto.location.toLocationEntity
import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AddLocationFavoriteTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var addFavorite: AddLocationFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<LocationDto>()

        // Act (When)
        val params = AddLocationFavorite.Params(dto)
        addFavorite.invoke(params)

        // Assert (Then)
        coVerify { addFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val dto = MockData.getLocationDto()
        val params = AddLocationFavorite.Params(dto)

        // Act (When)
        addFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.saveFavorite(dto.toLocationEntity())
        }
    }
}