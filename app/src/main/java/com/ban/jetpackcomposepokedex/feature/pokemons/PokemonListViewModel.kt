package com.ban.jetpackcomposepokedex.feature.pokemons

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.ban.jetpackcomposepokedex.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    reducer: PokemonListReducer,
    pokemonListMiddleware: PokemonListMiddleware,
    pokemonListSearchMiddleware: PokemonListSearchMiddleware
) : BaseViewModel<PokemonListViewState, PokemonListAction>(
    initialViewState = PokemonListViewState(),
    reducer = reducer,
    middlewares = listOf(pokemonListMiddleware, pokemonListSearchMiddleware)
) {

    init {
        val action = PokemonListAction.Init
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun searchInputChanged(query: String) {
        val action = PokemonListAction.SearchInputChanged(query)
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun bottomOfListReached() {
        val action = PokemonListAction.BottomOfListReached
        viewModelScope.launch {
            store.dispatch(action)
        }
    }

    fun calculateDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}