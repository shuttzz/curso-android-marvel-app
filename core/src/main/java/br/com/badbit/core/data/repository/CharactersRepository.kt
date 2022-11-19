package br.com.badbit.core.data.repository

import androidx.paging.PagingSource
import br.com.badbit.core.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>
}