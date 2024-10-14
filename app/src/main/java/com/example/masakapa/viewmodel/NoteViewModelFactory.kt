package com.example.masakapa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.masakapa.repository.NoteRepository

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory(private val noteRepository: NoteRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        return NoteViewModel(noteRepository) as T
    }
}