package com.example.selfnotesapp.View.Screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.selfnotesapp.Viewmodel.NoteViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.selfnotesapp.Model.Note


@Composable
fun NoteList(viewModel: NoteViewModel, padding: PaddingValues, onEdit: (Note) -> Unit) {
    val notes by viewModel.notes

    LazyColumn(
        contentPadding = padding,
        modifier = Modifier.fillMaxSize()
    ) {
        items(notes) { note ->
            NoteCard(note, onEdit = { onEdit(note) }, onDelete = { viewModel.deleteNote(note.id) })
        }
    }
}
