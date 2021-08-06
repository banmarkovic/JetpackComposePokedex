package com.ban.jetpackcomposepokedex.feature.pokemon

import com.ban.jetpackcomposepokedex.data.Resource
import com.ban.jetpackcomposepokedex.data.repository.PokemonRepository
import com.ban.jetpackcomposepokedex.redux.Middleware
import com.ban.jetpackcomposepokedex.redux.Store
import javax.inject.Inject

class PokemonListMiddleware @Inject constructor(
    private val repository: PokemonRepository
) : Middleware<PokemonDetailsViewState, PokemonDetailsAction> {

    override suspend fun process(
        action: PokemonDetailsAction,
        currentState: PokemonDetailsViewState,
        store: Store<PokemonDetailsViewState, PokemonDetailsAction>
    ) {
        if (action is PokemonDetailsAction.Init) {
            getPokemon(action.pokemonName, store)
        }
    }

    private suspend fun getPokemon(
        pokemonName: String,
        store: Store<PokemonDetailsViewState, PokemonDetailsAction>
    ) {
        store.dispatch(PokemonDetailsAction.PokemonDetailsFetchingStarted)

        when (val resp = repository.getPokemon(pokemonName)) {
            is Resource.Success -> {
                resp.data?.let { pokemon ->
                    store.dispatch(PokemonDetailsAction.PokemonDetailsFetchingCompleted(pokemon))
                }
            }
            is Resource.Error -> {
                store.dispatch(
                    PokemonDetailsAction.PokemonDetailsFetchingFailed(
                        resp.errorMessage ?: "Failed to fetch Pokemon"
                    )
                )
            }
        }
    }
}