package com.developersancho.domain.usecase.location

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.developersancho.framework.usecase.FlowPagingUseCase
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.repository.location.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocations @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: LocationRepository
) : FlowPagingUseCase<GetLocations.Params, LocationDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

    override fun execute(params: Params): Flow<PagingData<LocationDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { LocationPagingSource(repository, params.options) }
        ).flow
    }
}