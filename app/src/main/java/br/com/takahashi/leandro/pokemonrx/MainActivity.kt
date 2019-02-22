package br.com.takahashi.leandro.pokemonrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.takahashi.leandro.pokemonrx.R.id.idRecicler
import br.com.takahashi.leandro.pokemonrx.api.PokemonAPI
import br.com.takahashi.leandro.pokemonrx.api.PokemonService
import br.com.takahashi.leandro.pokemonrx.model.Pokemon
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    lateinit var adapter: PokemonAdapter
    val pokemons = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api = PokemonService()
        adapter = PokemonAdapter(this, pokemons, {})
        idRecicler.adapter = adapter
        idRecicler.layoutManager = LinearLayoutManager(this)

        api.loadPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                       pokemons.add(it)
                    },
                    {e -> e.printStackTrace()},
                    {Log.i("POKEMON", "msg todos os pokemons emitidos")}
                )
    }
}
