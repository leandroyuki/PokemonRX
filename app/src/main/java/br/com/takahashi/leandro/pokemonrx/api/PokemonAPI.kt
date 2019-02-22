package br.com.takahashi.leandro.pokemonrx.api

import br.com.takahashi.leandro.pokemonrx.model.Pokemon
import br.com.takahashi.leandro.pokemonrx.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface PokemonAPI {
    @GET("/api/v2/pokemon")
    fun listaPokemons(): Observable<PokemonResponse>
    @GET("/api/v2/pokemon/{nomePokemon}")
    fun buscarPor(@Path("nomePokemon") nomePokemon: String)
        : Observable<Pokemon>
}