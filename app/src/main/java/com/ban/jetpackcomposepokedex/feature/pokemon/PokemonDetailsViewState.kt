package com.ban.jetpackcomposepokedex.feature.pokemon

import com.ban.jetpackcomposepokedex.model.remote.Pokemon
import com.ban.jetpackcomposepokedex.redux.State

data class PokemonDetailsViewState(
    val pokemon: Pokemon? = null,
    val showProgressBar: Boolean = false,
    val error: String? = null
) : State