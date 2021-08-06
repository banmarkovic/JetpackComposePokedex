package com.ban.jetpackcomposepokedex.feature.pokemons

import com.ban.jetpackcomposepokedex.model.local.PokemonEntryData
import com.ban.jetpackcomposepokedex.redux.State

data class PokemonListViewState(
    val searchInput: String = "",
    val pokemonList: List<PokemonEntryData> = listOf(),
    val pokemonListBySearch: List<PokemonEntryData>? = null,
    val showProgressBar: Boolean = false,
    val error: String? = null
) : State