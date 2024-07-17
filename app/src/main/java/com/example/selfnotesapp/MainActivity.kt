package com.example.selfnotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.selfnotesapp.Model.Note
import com.example.selfnotesapp.View.Navigation.RootNavHost
import com.example.selfnotesapp.View.Screen.HomeScreen
import com.example.selfnotesapp.View.Screen.NoteEditor
import com.example.selfnotesapp.View.Screen.NoteList
import com.example.selfnotesapp.Viewmodel.AuthViewModel
import com.example.selfnotesapp.Viewmodel.NoteViewModel
import com.example.selfnotesapp.ui.theme.SelfNotesAppTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val authViewModel: AuthViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            SelfNotesAppTheme {
                //RootNavHost(authViewModel = authViewModel)
                //HomeScreen()
                val viewModel: NoteViewModel by viewModels()
                var isEditing by remember { mutableStateOf(false) }
                var editingNote by remember { mutableStateOf<Note?>(null) }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Notes") },
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            isEditing = true
                            editingNote = null
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Note")
                        }
                    }
                ) { padding ->
                    if (isEditing) {
                        NoteEditor(
                            note = editingNote,
                            onSave = {
                                if (editingNote == null) {
                                    viewModel.addNote(it)
                                } else {
                                    viewModel.updateNote(it)
                                }
                                isEditing = false
                            },
                            onCancel = { isEditing = false }
                        )
                    } else {
                        NoteList(
                            viewModel = viewModel,
                            padding = padding,
                            onEdit = { note ->
                                isEditing = true
                                editingNote = note
                            }
                        )
                    }
                }
            }
        }
    }
}

