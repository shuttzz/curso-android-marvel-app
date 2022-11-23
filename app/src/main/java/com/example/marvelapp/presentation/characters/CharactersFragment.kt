package com.example.marvelapp.presentation.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.badbit.core.domain.model.Character
import com.example.marvelapp.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding: FragmentCharactersBinding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels()

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

        /**
         * Preciso chamar o ViewModel dentro de um escopo de Coroutine, por isso estou utilizando o
         * lifecycleScope para poder me dá esse escopo
         */
        lifecycleScope.launch {
            viewModel.charactersPagingData("").collect { pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }

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
