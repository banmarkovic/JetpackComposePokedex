package com.ban.jetpackcomposepokedex.model.remote

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)