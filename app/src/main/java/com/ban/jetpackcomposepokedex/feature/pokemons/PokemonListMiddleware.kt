package com.ban.jetpackcomposepokedex.feature.pokemons

import com.ban.jetpackcomposepokedex.data.Resource
import com.ban.jetpackcomposepokedex.data.repository.PokemonRepository
import com.ban.jetpackcomposepokedex.model.local.PokemonEntryData
import com.ban.jetpackcomposepokedex.redux.Middleware
import com.ban.jetpackcomposepokedex.redux.Store
import javax.inject.Inject

private const val PAGE_SIZE = 20

class PokemonListMiddleware @Inject constructor(
    private val repository: PokemonRepository
) : Middleware<PokemonListViewState, PokemonListAction> {

    override suspend fun process(
        action: PokemonListAction,
        currentState: PokemonListViewState,
        store: Store<PokemonListViewState, PokemonListAction>
    ) {
        if (action is PokemonListAction.Init ||
            action is PokemonListAction.BottomOfListReached) {
            getPokemonList(currentState, store)
        }
    }

    private suspend fun getPokemonList(
        currentState: PokemonListViewState,
        store: Store<PokemonListViewState, PokemonListAction>
    ) {
        if (currentState.pokemonList.size % PAGE_SIZE != 0) {
            return
        }

        store.dispatch(PokemonListAction.PokemonListFetchingStarted)

        val resp = repository.getPokemonList(
            limit = PAGE_SIZE,
            offset = currentState.pokemonList.size
        )

        when (resp) {
            is Resource.Success -> {
                resp.data?.results?.let { pokemons ->
                    val updatedPokemonList = currentState.pokemonList + pokemons.map { pokemon ->
                        val number = if (pokemon.url.endsWith("/")) {
                            pokemon.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            pokemon.url.takeLastWhile { it.isDigit() }
                        }
                        val imageUrl =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonEntryData(
                            pokemonName = pokemon.name.capitalize(),
                            imageUrl = imageUrl,
                            number = number.toInt()
                        )
                    }

                    store.dispatch(PokemonListAction.PokemonListFetchingCompleted(updatedPokemonList))
                }
            }
            is Resource.Error -> {
                store.dispatch(
                    PokemonListAction.PokemonListFetchingFailed(
                        resp.errorMessage ?: "Failed to fetch Pokemons"
                    )
                )
            }
        }
    }
}