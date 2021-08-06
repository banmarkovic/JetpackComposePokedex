package com.ban.jetpackcomposepokedex.feature.pokemons

import com.ban.jetpackcomposepokedex.model.local.PokemonEntryData
import com.ban.jetpackcomposepokedex.redux.Action

sealed class PokemonListAction : Action {
    object Init : PokemonListAction()
    data class SearchInputChanged(val query: String) : PokemonListAction()
    object BottomOfListReached : PokemonListAction()

    object PokemonListFetchingStarted : PokemonListAction()
    data class PokemonListFetchingCompleted(val pokemonList: List<PokemonEntryData>) :
        PokemonListAction()

    data class PokemonListFetchingFailed(val error: String) : PokemonListAction()
    data class PokemonListSearched(val searchedPokemonList: List<PokemonEntryData>?) :
        PokemonListAction()
}