package com.ban.jetpackcomposepokedex.model.remote

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)