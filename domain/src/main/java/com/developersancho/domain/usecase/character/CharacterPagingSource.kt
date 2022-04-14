package com.developersancho.domain.usecase.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.developersancho.framework.extension.orZero
import com.developersancho.model.dto.character.CharacterDto
import com.developersancho.model.dto.character.toCharacterDtoList
import com.developersancho.repository.character.CharacterRepository
import java.io.IOException

class CharacterPagingSource(
    internal val repository: CharacterRepository,
    internal val options: Map<String, String>
) : PagingSource<Int, CharacterDto>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        // for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {
            val response = repository.getCharacterList(page, options)
            val characterList = response.results.orEmpty().toCharacterDtoList()

            characterList.map {
                val characterFav = repository.getFavorite(it.id.orZero())
                it.isFavorite = characterFav != null
            }

            LoadResult.Page(
                data = characterList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characterList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            // IOException for network failures.
            return LoadResult.Error(exception)
        }
    }
}