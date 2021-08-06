package com.ban.jetpackcomposepokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ban.jetpackcomposepokedex.feature.pokemon.PokemonDetailScreen
import com.ban.jetpackcomposepokedex.feature.pokemons.PokemonListScreen

enum class Screen(name: String) {
    PokemonList("pokemon_list_screen"),
    PokemonDetail("pokemon_detail_screen")
}

enum class Argument(name: String) {
    DominantColor("dominantColor"),
    PokemonName("pokemonName")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.PokemonList.name
    ) {
        composable(Screen.PokemonList.name) {
            PokemonListScreen(navController)
        }
        composable(
            "${Screen.PokemonDetail.name}/{${Argument.DominantColor.name}}/{${Argument.PokemonName.name}}",
            arguments = listOf(
                navArgument(Argument.DominantColor.name) {
                    type = NavType.IntType
                },
                navArgument(Argument.PokemonName.name) {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt(Argument.DominantColor.name)
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString(Argument.PokemonName.name).orEmpty()
            }

            PokemonDetailScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName,
                navController = navController
            )
        }
    }
}