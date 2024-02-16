package com.dam.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dam.pokedex.R
import com.dam.pokedex.databinding.PokemonViewBinding
import com.dam.pokedex.models.Pokemon.Pokemon

class PokemonAdapter(
    private var pokemons: MutableList<Pokemon>,
    private val listener: PokemonListener
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = PokemonViewBinding.bind(view)

        fun bind(pokemon: Pokemon){
            //Solo cargo la imagen
            Glide.with(binding.pokemonImage)
                .load(pokemon.sprites.front_default)
                .into(binding.pokemonImage)

            setupListeners(pokemon)
        }


        private fun setupListeners(pokemon: Pokemon) {
            binding.cardView.setOnClickListener{
                listener.onClick(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_view, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.PokemonViewHolder, position: Int) {
        val pokemon = pokemons.get(position)
        with(holder){
            bind(pokemon)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    fun submitList(filteredPokemons: List<Pokemon>) {
        this.pokemons = filteredPokemons as MutableList<Pokemon>
    }
}


