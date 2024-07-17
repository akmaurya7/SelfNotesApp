package com.example.selfnotesapp.Model

import com.google.firebase.firestore.FirebaseFirestore

class NoteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val notesCollection = db.collection("Notes")

    fun addNote(note: Note, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        notesCollection.add(note)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        notesCollection.document(note.id).set(note)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun deleteNote(noteId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        notesCollection.document(noteId).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getNotes(onSuccess: (List<Note>) -> Unit, onFailure: (Exception) -> Unit) {
        notesCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val notes = querySnapshot.documents.map { it.toObject(Note::class.java)!! }
                onSuccess(notes)
            }
            .addOnFailureListener { onFailure(it) }
    }
}
