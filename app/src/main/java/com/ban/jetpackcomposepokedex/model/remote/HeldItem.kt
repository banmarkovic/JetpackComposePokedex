package com.ban.jetpackcomposepokedex.model.remote

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)