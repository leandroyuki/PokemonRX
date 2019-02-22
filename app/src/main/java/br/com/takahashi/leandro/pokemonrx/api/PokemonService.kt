package br.com.takahashi.leandro.pokemonrx.api

import br.com.takahashi.leandro.pokemonrx.model.Pokemon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class PokemonService {
    val api: PokemonAPI
    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        api = retrofit.create(PokemonAPI::class.java)
    }
    fun loadPokemons() : Observable<Pokemon>{
        return api.listaPokemons()
                    .flatMap {Observable.from(it.results)}
                    .flatMap { api.buscarPor(it.name)}
    }


}