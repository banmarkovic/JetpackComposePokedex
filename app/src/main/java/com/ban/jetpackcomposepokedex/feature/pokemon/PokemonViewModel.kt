package com.ban.jetpackcomposepokedex.feature.pokemon

import androidx.lifecycle.viewModelScope
import com.ban.jetpackcomposepokedex.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonDetailsReducer: PokemonDetailsReducer,
    private val pokemonDetailsMiddleware: PokemonListMiddleware
) : BaseViewModel<PokemonDetailsViewState, PokemonDetailsAction>(
    initialViewState = PokemonDetailsViewState(),
    reducer = pokemonDetailsReducer,
    middlewares = listOf(pokemonDetailsMiddleware)
) {

    fun getPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            store.dispatch(PokemonDetailsAction.Init(pokemonName))
        }
    }
}