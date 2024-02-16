package com.dam.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dam.pokedex.MainActivity
import com.dam.pokedex.api.ApiService
import com.dam.pokedex.api.RetrofitServiceFactory
import com.dam.pokedex.databinding.FragmentPokemonDetailsBinding
import com.dam.pokedex.models.Pokemon.Pokemon
import com.dam.pokedex.models.PokemonDetails.PokemonDetails
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class PokemonDetailsFragment() : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding
    private lateinit var mActivity: MainActivity
    val service = RetrofitServiceFactory.makeRetrofitService()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mActivity = requireActivity() as MainActivity
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val pokemon = mActivity.mSharedViewModel.getActualPokemonValue()
            val pokemonDetails = service.getPokemonSpecies(pokemon!!.id)
            setUpBindings(pokemonDetails, pokemon)
        }

    }

    private fun setUpBindings(pokemonDetails: PokemonDetails, pokemon: Pokemon) {


        Glide.with(binding.imageView)
            .load(pokemon!!.sprites.front_default)
            .into(binding.imageView)

        val types = pokemon.types.map { it.type.name }

        binding.nameField.text = pokemon.name
        binding.typeField.text = types.toString()
        binding.detailsField.text =  pokemonDetails.flavor_text_entries[0].flavor_text

        binding.backButton.setOnClickListener {
            mActivity.showFragment(PokemonListFragment())
        }
    }

}