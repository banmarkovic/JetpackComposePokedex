package com.ban.jetpackcomposepokedex.feature.pokemons

import com.ban.jetpackcomposepokedex.redux.Reducer
import javax.inject.Inject

class PokemonListReducer @Inject constructor() : Reducer<PokemonListViewState, PokemonListAction> {
    override fun reduce(
        currentState: PokemonListViewState,
        action: PokemonListAction
    ): PokemonListViewState {
        return when (action) {
            PokemonListAction.PokemonListFetchingStarted -> stateAfterFetchingStarted(currentState)
            is PokemonListAction.PokemonListFetchingCompleted -> stateAfterFetchingCompleted(
                currentState,
                action
            )
            is PokemonListAction.PokemonListFetchingFailed -> stateAfterFetchingFailed(
                currentState,
                action
            )
            is PokemonListAction.SearchInputChanged -> stateAfterSearchInputChanged(
                currentState = currentState,
                action = action
            )
            is PokemonListAction.PokemonListSearched -> stateAfterSearch(currentState, action)
            else -> currentState
        }
    }

    private fun stateAfterFetchingStarted(currentState: PokemonListViewState) =
        currentState.copy(showProgressBar = true)

    private fun stateAfterFetchingCompleted(
        currentState: PokemonListViewState,
        action: PokemonListAction.PokemonListFetchingCompleted
    ) =
        currentState.copy(
            pokemonList = action.pokemonList,
            pokemonListBySearch = null,
            showProgressBar = false
        )

    private fun stateAfterFetchingFailed(
        currentState: PokemonListViewState,
        action: PokemonListAction.PokemonListFetchingFailed
    ) =
        currentState.copy(
            showProgressBar = false,
            error = action.error
        )

    private fun stateAfterSearchInputChanged(
        currentState: PokemonListViewState,
        action: PokemonListAction.SearchInputChanged
    ) =
        currentState.copy(searchInput = action.query)

    private fun stateAfterSearch(
        currentState: PokemonListViewState,
        action: PokemonListAction.PokemonListSearched
    ) =
        currentState.copy(
            pokemonListBySearch = action.searchedPokemonList
        )
}