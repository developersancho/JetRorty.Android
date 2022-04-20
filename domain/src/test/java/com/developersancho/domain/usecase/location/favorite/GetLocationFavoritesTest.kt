package com.developersancho.domain.usecase.location.favorite

import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetLocationFavoritesTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var getFavorites: GetLocationFavorites

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getFavorites.invoke(Unit)

        // Assert (Then)
        coVerify { getFavorites.invoke(Unit) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)

        // Act (When)
        getFavorites.invoke(Unit).single()

        // Assert (Then)
        coVerify { repository.getFavoriteList() }
    }
}