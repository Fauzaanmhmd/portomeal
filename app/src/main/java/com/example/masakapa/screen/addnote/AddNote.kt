package com.example.masakapa.screen.addnote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.masakapa.data.model.Note
import com.example.masakapa.viewmodel.NoteViewModel

@Composable
fun AddNote(noteId: Int? = null, onNavigateToNoteList: () -> Unit) {
    val noteViewModels: NoteViewModel = hiltViewModel()
    val note = noteViewModels.getNoteById(noteId ?: 0).observeAsState(null).value
    // Cari catatan berdasarkan ID jika tersedia

    var title by remember { mutableStateOf(TextFieldValue(note?.title ?: "")) }
    var description by remember { mutableStateOf(TextFieldValue(note?.description ?: "")) }

    // Mengisi nilai title dan description setelah data catatan tersedia
    LaunchedEffect(note) {
        note?.let {
            title = TextFieldValue(it.title)
            description = TextFieldValue(it.description)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Judul
        Text(
            text = if (note != null) "Edit Data" else "Tambah Data",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input Field untuk Title
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Input Field untuk Description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Button untuk Menambah Value
        Button(
            onClick = {
                if (note != null) {
                    // Update catatan
                    noteViewModels.update(
                        note.copy(
                            title = title.text,
                            description = description.text
                        )
                    )
                } else {
                    // Tambah catatan baru
                    noteViewModels.insert(Note(null, title.text, description.text))
                }
                onNavigateToNoteList()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (note != null) "Update" else "Tambah")
        }
    }
}
