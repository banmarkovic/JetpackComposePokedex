package com.ban.jetpackcomposepokedex.data.repository

import com.ban.jetpackcomposepokedex.data.Resource
import com.ban.jetpackcomposepokedex.data.source.PokemonApi
import com.ban.jetpackcomposepokedex.model.remote.Pokemon
import com.ban.jetpackcomposepokedex.model.remote.PokemonList
import java.lang.Exception
import javax.inject.Inject

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemon(name: String): Resource<Pokemon>
}

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return try {
            Resource.Success(api.getPokemonList(limit, offset))
        } catch (e: Exception) {
            Resource.Error("Unknown error occurred")
        }
    }

    override suspend fun getPokemon(name: String): Resource<Pokemon> {
        return try {
            Resource.Success(api.getPokemon(name.toLowerCase()))
        } catch (e: Exception) {
            Resource.Error("Unknown error occurred")
        }
    }
}