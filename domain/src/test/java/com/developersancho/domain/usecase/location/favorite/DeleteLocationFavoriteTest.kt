package com.developersancho.domain.usecase.location.favorite

import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteLocationFavoriteTest : MockkUnitTest() {
    @MockK(relaxed = true)
    lateinit var repository: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var deleteFavorite: DeleteLocationFavorite

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val id = 1

        // Act (When)
        val params = DeleteLocationFavorite.Params(id)
        deleteFavorite.invoke(params)

        // Assert (Then)
        coVerify { deleteFavorite.invoke(any()) }
    }

    @Test
    fun collectExecute() = runTest {
        // Arrange (Given)
        val id = 1
        val params = DeleteLocationFavorite.Params(id)

        // Act (When)
        deleteFavorite.invoke(params).single()

        // Assert (Then)
        coVerify {
            repository.deleteFavoriteById(id)
        }
    }
}