package com.dam.pokedex.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dam.pokedex.MainActivity
import com.dam.pokedex.adapter.PokemonAdapter
import com.dam.pokedex.adapter.PokemonListener
import com.dam.pokedex.api.RetrofitServiceFactory
import com.dam.pokedex.databinding.FragmentPokemonListBinding
import com.dam.pokedex.models.Pokemon.Pokemon
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class PokemonListFragment : Fragment(), PokemonListener {

    private lateinit var binding: FragmentPokemonListBinding
   // private var pokemonList: MutableList<Pokemon> = mutableListOf()

    private val service = RetrofitServiceFactory.makeRetrofitService()
    private lateinit var mAdapter: PokemonAdapter
    private lateinit var mActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mActivity = requireActivity() as MainActivity
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var pokemonList:MutableList<Pokemon> = mutableListOf()

        lifecycleScope.launch {
            if (mActivity.mSharedViewModel.getMyPokemons().size == 0){
                for (i in 1..100) {
                    val pokemonInfo = service.getPokemonInfo(i)
                    // pokemonList.add(pokemonInfo)
                    mActivity.mSharedViewModel.addToMyPokemons(pokemonInfo)
                    pokemonList = mActivity.mSharedViewModel.getMyPokemons()
                }
            }else{
                pokemonList = mActivity.mSharedViewModel.getMyPokemons()
            }

            setUpRecyclerView(pokemonList)
            setUpBindings(pokemonList)
        }
    }

    private fun setUpBindings(pokemonList: MutableList<Pokemon>) {


        binding.searchName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar este método
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar este método
            }

            override fun afterTextChanged(s: Editable?) {
                filterList(pokemonList)
            }
        })
    }



    private fun filterList(pokemonList: MutableList<Pokemon>) {
        var filteredPokemons = mutableListOf<Pokemon>()

        if (binding.searchName.text.toString().isNotEmpty()) {
            filteredPokemons = pokemonList.filter { it.name.trim().contains(binding.searchName.text.toString().trim(), ignoreCase = true) } as MutableList<Pokemon>
        } else {
            filteredPokemons = pokemonList
        }

        setUpRecyclerView(filteredPokemons)
    }
    private fun setUpRecyclerView(pokemonList: MutableList<Pokemon>) {
        mAdapter = PokemonAdapter(
            pokemonList, this
        )
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }

    override fun onClick(pokemon: Pokemon) {
        mActivity.mSharedViewModel.setActualPokemonValue(pokemon)
        mActivity.showFragment(PokemonDetailsFragment())
    }
}
