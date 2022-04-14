package com.developersancho.domain.usecase.location

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.extension.isNull
import com.developersancho.framework.extension.orZero
import com.developersancho.framework.network.DataState
import com.developersancho.framework.network.apiCall
import com.developersancho.framework.usecase.DataStateUseCase
import com.developersancho.model.dto.character.toCharacterDto
import com.developersancho.model.dto.location.LocationDto
import com.developersancho.model.dto.location.toLocationDto
import com.developersancho.repository.character.CharacterRepository
import com.developersancho.repository.location.LocationRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetLocationDetail
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val locRepo: LocationRepository,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val charRepo: CharacterRepository
) : DataStateUseCase<GetLocationDetail.Params, LocationDto>() {

    data class Params(
        val url: String? = null,
        val detailId: Int? = null
    )

    override suspend fun FlowCollector<DataState<LocationDto>>.execute(params: Params) {
        val getLocation = if (params.detailId.isNull()) {
            locRepo.getLocation(url = params.url.orEmpty()).toLocationDto()
        } else {
            locRepo.getLocation(params.detailId.orZero()).toLocationDto()
        }
        val service = apiCall { getLocation }
        service.map { dto ->
            val favorite = locRepo.getFavorite(dto.id.orZero())
            dto.isFavorite = favorite != null

            dto.residents.forEach {
                val characterService = apiCall { charRepo.getCharacter(it) }
                if (characterService is DataState.Success) {
                    dto.residentDtoList.add(characterService.result.toCharacterDto())
                }
            }
        }
        emit(service)
    }
}