package com.gabrielbenini.noteyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielbenini.noteyapp.repository.NoteRepository
import com.gabrielbenini.noteyapp.roomdb.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val repository : NoteRepository) : ViewModel() { // Passamos o repository nos parametros da funcao para usar as funcoes dela aq

    val allNotes: LiveData<List<Note>> = repository.allNotes // Exposição das notas vindas do banco para a UI observar

    fun insert(note: Note) = viewModelScope.launch {     // Executa operação de escrita fora da thread principal

        repository.insert(note)
    }

}