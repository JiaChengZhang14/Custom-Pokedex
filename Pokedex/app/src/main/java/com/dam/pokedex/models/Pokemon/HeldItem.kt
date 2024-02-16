package com.dam.pokedex.models.Pokemon

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)