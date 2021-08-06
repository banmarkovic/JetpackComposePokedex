package com.ban.jetpackcomposepokedex.data.source

import com.ban.jetpackcomposepokedex.model.remote.Pokemon
import com.ban.jetpackcomposepokedex.model.remote.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon
}