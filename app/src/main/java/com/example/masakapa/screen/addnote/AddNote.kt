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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.masakapa.data.AppDataBase
import com.example.masakapa.data.model.Note
import com.example.masakapa.repository.NoteRepository
import com.example.masakapa.viewmodel.NoteViewModel
import com.example.masakapa.viewmodel.NoteViewModelFactory

@Composable
fun AddNote(onNavigateToNoteList: () -> Unit) {

    // sebelum pake hilt
     val context = LocalContext.current

//    val noteViewModel: NoteViewModel = viewModel(
//        factory = NoteViewModelFactory(
//            NoteRepository(AppDataBase.getInstance(context))
//        )
//    )

    val noteViewModels: NoteViewModel = hiltViewModel()


    // State untuk input title dan description
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Judul
        Text(
            text = "Tambah Data",
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
                onNavigateToNoteList()
                result = "${title.text} ${description.text}"
                noteViewModels.insert(Note(null, title.text, description.text))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Tambah")
        }
    }
}

@Preview
@Composable
fun AddNoteScreen() {
    AddNote({})
}
