package com.developersancho.locations.list

import androidx.paging.PagingData
import app.cash.turbine.test
import com.developersancho.domain.mockdata.MockData
import com.developersancho.domain.usecase.location.GetLocations
import com.developersancho.domain.usecase.location.favorite.DeleteLocationFavorite
import com.developersancho.domain.usecase.location.favorite.GetLocationFavorites
import com.developersancho.domain.usecase.location.favorite.UpdateLocationFavorite
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.testutils.MockkUnitTest
import com.google.common.truth.Truth
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LocationsViewModelTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var getLocations: GetLocations

    @RelaxedMockK
    lateinit var updateFavorite: UpdateLocationFavorite

    @MockK(relaxed = true)
    lateinit var getFavorites: GetLocationFavorites

    @MockK(relaxed = true)
    lateinit var deleteFavorite: DeleteLocationFavorite

    @SpyK
    @InjectMockKs
    lateinit var viewModel: LocationsViewModel

    @Test
    fun verifyOnTriggerEventLoadLocations() = runTest {
        // Arrange (Given)
        every { getLocations.invoke(any()) } returns flow {
            emit(PagingData.from(MockData.getLocationDtoList()))
        }

        // Act (When)
        viewModel.onTriggerEvent(LocationsEvent.LoadLocations)

        // Assert (Then)
        verify { getLocations.invoke(any()) }
    }

    @Test
    fun verifyOnTriggerEventAddOrRemoveFavorite() = runTest {
        // Arrange (Given)
        val dto = MockData.getLocationDto()

        // Act (When)
        viewModel.onTriggerEvent(LocationsEvent.AddOrRemoveFavorite(dto))

        // Assert (Then)
        coVerify { updateFavorite(UpdateLocationFavorite.Params(dto)) }
    }

    @Test
    fun verifyOnTriggerEventGetFavorites() = runTest {
        // Arrange (Given)

        // Act (When)
        viewModel.onTriggerEvent(LocationsEvent.LoadFavorites)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun verifyOnTriggerEventDeleteFavorite() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        viewModel.onTriggerEvent(LocationsEvent.DeleteFavorite(id))

        // Assert (Then)
        coVerify { deleteFavorite(DeleteLocationFavorite.Params(id)) }
    }

    @Test
    fun verifyCheckState() = runTest {
        // Arrange (Given)
        val response = PagingData.from(MockData.getLocationDtoList())
        every { getLocations(any()) } returns flow { emit(response) }

        // Act (When)
        viewModel.onTriggerEvent(LocationsEvent.LoadLocations)

        // Assert (Then)
        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState::class.java)
            }
        }
    }
}