package com.ban.jetpackcomposepokedex.feature.pokemon

import com.ban.jetpackcomposepokedex.redux.Reducer
import javax.inject.Inject

class PokemonDetailsReducer @Inject constructor() :
    Reducer<PokemonDetailsViewState, PokemonDetailsAction> {
    override fun reduce(
        currentState: PokemonDetailsViewState,
        action: PokemonDetailsAction
    ): PokemonDetailsViewState {
        return when (action) {
            PokemonDetailsAction.PokemonDetailsFetchingStarted -> stateAfterFetchingStarted(
                currentState
            )
            is PokemonDetailsAction.PokemonDetailsFetchingCompleted -> stateAfterFetchingCompleted(
                currentState,
                action
            )
            is PokemonDetailsAction.PokemonDetailsFetchingFailed -> stateAfterFetchingFailed(
                currentState,
                action
            )
            else -> currentState
        }
    }

    private fun stateAfterFetchingStarted(currentState: PokemonDetailsViewState) =
        currentState.copy(showProgressBar = true)

    private fun stateAfterFetchingCompleted(
        currentState: PokemonDetailsViewState,
        action: PokemonDetailsAction.PokemonDetailsFetchingCompleted
    ) =
        currentState.copy(
            showProgressBar = false,
            pokemon = action.pokemon
        )

    private fun stateAfterFetchingFailed(
        currentState: PokemonDetailsViewState,
        action: PokemonDetailsAction.PokemonDetailsFetchingFailed
    ) =
        currentState.copy(
            showProgressBar = false,
            error = action.error
        )
}