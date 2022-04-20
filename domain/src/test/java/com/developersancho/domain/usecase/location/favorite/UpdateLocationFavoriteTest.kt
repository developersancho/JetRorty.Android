package com.developersancho.domain.usecase.location.favorite

import com.developersancho.domain.mockdata.MockData
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.dto.location.toLocationEntity
import com.developersancho.model.local.location.LocationEntity
import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpdateLocationFavoriteTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LocationRepository

    @SpyK
    @MockK
    lateinit var entity: LocationEntity

    @SpyK
    @InjectMockKs
    private lateinit var updateFavorite: UpdateLocationFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val dto = mockk<LocationDto>()

        // Act (When)
        val params = UpdateLocationFavorite.Params(dto)
        updateFavorite.invoke(params)

        // Assert (Then)
        coVerify { updateFavorite.invoke(any()) }
    }

    @Test
    fun collectExecuteSave() = runTest {
        // Arrange (Given)
        val dto = MockData.getLocationDto()
        val params = UpdateLocationFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns null

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.saveFavorite(dto.toLocationEntity()) }
    }

    @Test
    fun collectExecuteDelete() = runTest {
        // Arrange (Given)
        val dto = MockData.getLocationDto()
        val params = UpdateLocationFavorite.Params(dto)

        coEvery { repository.getFavorite(dto.id.orZero()) } returns entity

        // Act (When)
        updateFavorite.invoke(params).single()

        // Assert (Then)
        coVerify { repository.deleteFavoriteById(dto.id.orZero()) }
    }
}