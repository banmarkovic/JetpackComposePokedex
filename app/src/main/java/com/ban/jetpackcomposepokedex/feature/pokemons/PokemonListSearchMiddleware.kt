package com.ban.jetpackcomposepokedex.feature.pokemons

import com.ban.jetpackcomposepokedex.redux.Middleware
import com.ban.jetpackcomposepokedex.redux.Store
import javax.inject.Inject

class PokemonListSearchMiddleware @Inject constructor() :
    Middleware<PokemonListViewState, PokemonListAction> {

    override suspend fun process(
        action: PokemonListAction,
        currentState: PokemonListViewState,
        store: Store<PokemonListViewState, PokemonListAction>
    ) {
        if (action is PokemonListAction.SearchInputChanged) {
            searchPokemon(action.query, currentState, store)
        }
    }

    private suspend fun searchPokemon(
        query: String,
        currentState: PokemonListViewState,
        store: Store<PokemonListViewState, PokemonListAction>
    ) {
        if (query.isEmpty()) {
            store.dispatch(PokemonListAction.PokemonListSearched(null))
            return
        }

        val filteredList = currentState.pokemonList.filter {
            it.pokemonName.contains(query, ignoreCase = true) ||
                    it.number.toString() == query
        }

        store.dispatch(PokemonListAction.PokemonListSearched(filteredList))
    }
}