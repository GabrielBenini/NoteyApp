package com.gabrielbenini.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabrielbenini.noteyapp.repository.NoteRepository
import com.gabrielbenini.noteyapp.roomdb.Note
import com.gabrielbenini.noteyapp.roomdb.NotesDB
import com.gabrielbenini.noteyapp.screens.DisplayDialog
import com.gabrielbenini.noteyapp.screens.DisplayNotesList
import com.gabrielbenini.noteyapp.ui.theme.NoteyAppTheme
import com.gabrielbenini.noteyapp.viewmodel.NoteViewModel
import com.gabrielbenini.noteyapp.viewmodel.NoteViewModelFactory
import kotlin.collections.emptyList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Room DB
        val database = NotesDB.getInstance(applicationContext)

        //repository
        val repository = NoteRepository(database.notesDao)

        // ViewModel Factory
        val viewModelFactory = NoteViewModelFactory(repository)

        //ViewModel
        val noteViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[NoteViewModel::class.java]

        setContent {
            NoteyAppTheme {

                Scaffold(
                    floatingActionButton = { MyFAB(viewModel = noteViewModel) }
                ) {


                    val notes by noteViewModel
                        .allNotes.observeAsState(emptyList())

                    DisplayNotesList(note = notes)

                }





            }
        }
    }
}

@Composable
fun MyFAB(viewModel: NoteViewModel){

    var showDialog by remember { mutableStateOf(false) }

    DisplayDialog(
        viewModel = viewModel,
        showDialog = showDialog
    ) {
        showDialog = false
    }

    FloatingActionButton(
        onClick = {
            showDialog = true
        },
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note"
        )

    }

}
