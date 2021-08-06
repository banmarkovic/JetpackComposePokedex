package com.ban.jetpackcomposepokedex.feature.pokemon

import com.ban.jetpackcomposepokedex.model.remote.Pokemon
import com.ban.jetpackcomposepokedex.redux.Action

sealed class PokemonDetailsAction : Action {
    data class Init(val pokemonName: String) : PokemonDetailsAction()

    object PokemonDetailsFetchingStarted : PokemonDetailsAction()
    data class PokemonDetailsFetchingCompleted(val pokemon: Pokemon) :
        PokemonDetailsAction()

    data class PokemonDetailsFetchingFailed(val error: String) : PokemonDetailsAction()
}