package com.developersancho.domain.usecase.location

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.dto.location.toLocationDtoList
import com.developersancho.repository.location.LocationRepository
import java.io.IOException

class LocationPagingSource(
    internal val repository: LocationRepository,
    internal val options: Map<String, String>
) : PagingSource<Int, LocationDto>() {
    override fun getRefreshKey(state: PagingState<Int, LocationDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getLocationList(page, options)
            val locationList = response.results.orEmpty().toLocationDtoList()

            locationList.map {
                val locationFav = repository.getFavorite(it.id.orZero())
                it.isFavorite = locationFav != null
            }

            LoadResult.Page(
                data = locationList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (locationList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}