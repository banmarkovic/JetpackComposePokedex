package com.ban.jetpackcomposepokedex.model.remote

data class Pokemon(
    val abilities: List<Ability>? = null,
    val base_experience: Int? = null,
    val forms: List<Form>? = null,
    val game_indices: List<GameIndice>? = null,
    val height: Int? = null,
    val held_items: List<HeldItem>? = null,
    val id: Int? = null,
    val is_default: Boolean? = null,
    val location_area_encounters: String? = null,
    val moves: List<Move>? = null,
    val name: String? = null,
    val order: Int? = null,
    val past_types: List<Any>? = null,
    val species: Species? = null,
    val sprites: Sprites? = null,
    val stats: List<Stat>? = null,
    val types: List<Type>? = null,
    val weight: Int? = null
)