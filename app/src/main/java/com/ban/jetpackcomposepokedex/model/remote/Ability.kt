package com.ban.jetpackcomposepokedex.model.remote

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)