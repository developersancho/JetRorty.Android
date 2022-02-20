package com.developersancho.jetrorty.features.characters
//
//import androidx.paging.PagingData
//import com.developersancho.model.dto.CharacterDto
//import kotlinx.coroutines.flow.Flow
//
//data class CharactersViewState(val pagedData: Flow<PagingData<CharacterDto>>)
//
//sealed class CharactersEvent {
//    object LoadCharacters : CharactersEvent()
//    data class UpdateFavorite(val characterDto: CharacterDto) : CharactersEvent()
//}