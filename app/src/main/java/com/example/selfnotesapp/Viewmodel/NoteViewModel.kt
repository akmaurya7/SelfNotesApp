package com.example.selfnotesapp.Viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.selfnotesapp.Model.Note
import com.example.selfnotesapp.Model.NoteRepository

class NoteViewModel : ViewModel() {
    private val repository = NoteRepository()

    private val _notes = mutableStateOf<List<Note>>(emptyList())
    val notes: State<List<Note>> = _notes

    init {
        getNotes()
    }

    fun addNote(note: Note) {
        repository.addNote(note, {
            getNotes()
        }, {
            Log.e("NoteViewModel", "Failed to add note", it)
        })
    }

    fun updateNote(note: Note) {
        repository.updateNote(note, {
            getNotes()
        }, {
            Log.e("NoteViewModel", "Failed to update note", it)
        })
    }

    fun deleteNote(noteId: String) {
        repository.deleteNote(noteId, {
            getNotes()
        }, {
            Log.e("NoteViewModel", "Failed to delete note", it)
        })
    }

    fun getNotes() {
        repository.getNotes({
            _notes.value = it
        }, {
            Log.e("NoteViewModel", "Failed to fetch notes", it)
        })
    }
}
