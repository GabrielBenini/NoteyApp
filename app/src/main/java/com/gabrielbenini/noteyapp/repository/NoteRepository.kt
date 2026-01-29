package com.gabrielbenini.noteyapp.repository

import androidx.lifecycle.LiveData
import com.gabrielbenini.noteyapp.roomdb.Note
import com.gabrielbenini.noteyapp.roomdb.NoteDao


// Repository: Serves as a single source of truth
// for data in your App, Handling all Data Ops:
// 1 - Fetching data from the network
// 2- Loading Data from a local DB

class NoteRepository(private val noteDao: NoteDao) {

    // Todas as funcoes da nossa Dao devem estar aqui na repository

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        return noteDao.insert(note)
    }

}