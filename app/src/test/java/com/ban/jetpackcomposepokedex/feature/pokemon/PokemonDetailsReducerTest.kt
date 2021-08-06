package com.ban.jetpackcomposepokedex.feature.pokemon

import com.ban.jetpackcomposepokedex.model.remote.Pokemon
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonDetailsReducerTest {

    @Test
    fun `reduce should return Pokemon in new state when PokemonDetailsFetchingCompleted`() {
        val pokemonName = "Pikachu"
        val initState = PokemonDetailsViewState()
        val fetchCompletedAction =
            PokemonDetailsAction.PokemonDetailsFetchingCompleted(Pokemon(name = pokemonName))

        val expectedState = PokemonDetailsViewState(
            showProgressBar = false,
            error = null,
            pokemon = Pokemon(name = pokemonName)
        )

        val reducer = PokemonDetailsReducer()
        val newState = reducer.reduce(initState, fetchCompletedAction)

        assertEquals(expectedState, newState)
    }

    @Test
    fun `reduce should return showProgress in new state when PokemonDetailsFetchingStarted`() {
        val initState = PokemonDetailsViewState()
        val fetchStartedAction =
            PokemonDetailsAction.PokemonDetailsFetchingStarted

        val expectedState = PokemonDetailsViewState(
            showProgressBar = true,
            error = null,
            pokemon = null
        )

        val reducer = PokemonDetailsReducer()
        val newState = reducer.reduce(initState, fetchStartedAction)

        assertEquals(expectedState, newState)
    }

    @Test
    fun `reduce should return error in new state when PokemonDetailsFetchingFailed`() {
        val error = "Failed to fetch"
        val initState = PokemonDetailsViewState()
        val fetchFailedAction =
            PokemonDetailsAction.PokemonDetailsFetchingFailed(error)

        val expectedState = PokemonDetailsViewState(
            showProgressBar = false,
            error = error,
            pokemon = null
        )

        val reducer = PokemonDetailsReducer()
        val newState = reducer.reduce(initState, fetchFailedAction)

        assertEquals(expectedState, newState)
    }
}