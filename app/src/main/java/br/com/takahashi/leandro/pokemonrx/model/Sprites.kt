package br.com.takahashi.leandro.pokemonrx.model

import com.google.gson.annotations.SerializedName

data class Sprites(
        @SerializedName("front_default")
        val frontDefault: String
)