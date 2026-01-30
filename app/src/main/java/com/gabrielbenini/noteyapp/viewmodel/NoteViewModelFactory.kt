package com.gabrielbenini.noteyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielbenini.noteyapp.repository.NoteRepository

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory { //    // Factory responsável por criar o NoteViewModel com dependências

    override fun <T : ViewModel> create(modelClass: Class<T>): T {  // Métod chamado pelo Android para criar o ViewModel


        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){  // Verifica se o ViewModel solicitado é o NoteViewModel


            return NoteViewModel(repository) as T
            // Cria o NoteViewModel passando o repository
        }
        throw IllegalArgumentException("Unknown View Model Class")

    }

}
