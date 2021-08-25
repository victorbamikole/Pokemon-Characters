package com.example.reactiveprogramming.model

data class Abilities (

    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int,
    val sprites: Sprites
)