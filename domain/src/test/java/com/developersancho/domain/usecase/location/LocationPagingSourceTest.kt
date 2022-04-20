package com.developersancho.domain.usecase.location

import androidx.paging.PagingSource
import com.developersancho.domain.mockdata.MockData
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.dto.location.toLocationDtoList
import com.developersancho.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class LocationPagingSourceTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var repository: LocationRepository

    @InjectMockKs
    lateinit var options: HashMap<String, String>

    @InjectMockKs
    lateinit var pagingSource: LocationPagingSource

    @Test
    fun `paging source prepend - success`() = runTest {
        // Arrange (Given)
        val response = MockData.getLocationResponse()
        coEvery { repository.getLocationList(any(), any()) } returns response

        // Act (When)
        val expectedResult = PagingSource.LoadResult.Page(
            data = response.results.orEmpty().toLocationDtoList(),
            prevKey = -1,
            nextKey = null
        )

        // Assert (Then)
        Assert.assertEquals(
            expectedResult,
            pagingSource.load(
                PagingSource.LoadParams.Prepend(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `paging source append - success`() = runTest {
        // Arrange (Given)
        val response = MockData.getLocationResponse()
        coEvery { repository.getLocationList(any(), any()) } returns response

        // Act (When)
        val expectedResult = PagingSource.LoadResult.Page(
            data = response.results.orEmpty().toLocationDtoList(),
            prevKey = 2,
            nextKey = null
        )

        // Assert (Then)
        Assert.assertEquals(
            expectedResult,
            pagingSource.load(
                PagingSource.LoadParams.Append(
                    key = 3,
                    loadSize = 20,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `paging source refresh - success`() = runTest {
        // Arrange (Given)
        val response = MockData.getLocationResponse()
        coEvery { repository.getLocationList(any(), any()) } returns response

        // Act (When)
        val expectedResult = PagingSource.LoadResult.Page(
            data = response.results.orEmpty().toLocationDtoList(),
            prevKey = 2,
            nextKey = null
        )

        // Assert (Then)
        Assert.assertEquals(
            expectedResult,
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 3,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `paging source load - failure - received null`() = runTest {
        // Arrange (Given)
        val error = NullPointerException()
        coEvery { repository.getLocationList(any(), any()) } throws error

        // Act (When)
        val expectedResult = PagingSource.LoadResult.Error<Int, LocationDto>(error)

        // Assert (Then)
        var exceptionThrown: Boolean = false
        try {
            Assert.assertEquals(
                expectedResult.toString(),
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = 0,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ).toString()
            )
        } catch (exception: NullPointerException) {
            // Maybe put some assertions on the exception here.
            exceptionThrown = true
        }
        Assert.assertTrue(exceptionThrown)
    }

    @Test
    fun `paging source load - failure - received http error`() = runTest {
        // Arrange (Given)
        val error = RuntimeException("404", Throwable())
        coEvery { repository.getLocationList(any(), any()) }.throws(error)

        // Act (When)
        val expectedResult = PagingSource.LoadResult.Error<Int, LocationDto>(error)
        // Assert (Then)
        var exceptionThrown: Boolean = false
        try {
            Assert.assertEquals(
                expectedResult.toString(),
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = 0,
                        loadSize = 1,
                        placeholdersEnabled = false
                    )
                ).toString()
            )
        } catch (exception: RuntimeException) {
            // Maybe put some assertions on the exception here.
            exceptionThrown = true
        }
        Assert.assertTrue(exceptionThrown)
    }
}