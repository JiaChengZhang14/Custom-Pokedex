package com.dam.pokedex.models.Pokemon

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)