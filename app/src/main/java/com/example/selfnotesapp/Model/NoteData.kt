package com.example.selfnotesapp.Model

import com.google.firebase.firestore.DocumentId

data class Note(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
