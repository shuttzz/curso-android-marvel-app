package com.example.marvelapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.badbit.core.domain.model.Character
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        charactersAdapter.submitList(
            listOf(
                Character(
                    "Spider Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "Spider Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "Spider Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "Spider Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
                Character(
                    "Spider Man",
                    "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
                ),
            )
        )

        initCharactersAdapter()
    }

    private fun initCharactersAdapter() {
        with(binding.recyclerCharacters) {
            // Essa propriedade só serve ser true porque no nosso layout o tamanho tá fixo, ou seja
            // não vai sofrer variação de tamanho o layout do item e então com essa propriedade fica
            // mais performático
            setHasFixedSize(true)

            adapter = charactersAdapter
        }
    }
}
