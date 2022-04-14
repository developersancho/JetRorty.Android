package com.developersancho.remote.service

import com.developersancho.model.remote.character.CharacterInfo
import com.developersancho.model.remote.character.CharacterResponse
import retrofit2.http.*

interface CharacterService {
    @GET(CHARACTER)
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>? = null
    ): CharacterResponse

    @GET("$CHARACTER/{id}")
    suspend fun getCharacter(
        @Path("id") characterId: Int
    ): CharacterInfo

    @GET
    suspend fun getCharacter(
        @Url url: String
    ): CharacterInfo

    companion object {
        const val CHARACTER = "character"
    }
}