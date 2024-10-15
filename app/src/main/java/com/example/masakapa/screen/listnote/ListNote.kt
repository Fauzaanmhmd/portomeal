package com.example.masakapa.screen.listnote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.masakapa.data.model.Note
import com.example.masakapa.viewmodel.NoteViewModel
import com.plcoding.typesafecomposenavigation.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNote(onNavigateToAddNote: (Int?) -> Unit) {
    val noteViewModels: NoteViewModel = hiltViewModel()
    val notes by noteViewModels.getAll().observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<Note?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Learning")

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(notes.size) { index ->
                    val data = notes[index]
                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = { /* Single Click Action */ },
                                onLongClick = {
                                    selectedNote = data
                                    showDialog = true
                                }
                            ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = data.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(
                                text = data.description,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                        }
                    }

                    // Dialog untuk Edit dan Delete
                    if (showDialog && selectedNote != null) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Opsi") },
                            text = { Text("Pilih tindakan untuk catatan ini") },
                            confirmButton = {
                                TextButton(onClick = {
                                    // Aksi Edit
                                    data.id?.let { onNavigateToAddNote(it) }
                                    showDialog = false
                                }) {
                                    Text("Edit")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    // Aksi Delete
                                    noteViewModels.delete(selectedNote!!)
                                    showDialog = false
                                }) {
                                    Text("Delete")
                                }
                            }
                        )
                    }

                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_search_24),
            contentDescription = null,
            modifier = Modifier
                .padding(32.dp)
                .background(Color.LightGray, shape = CircleShape)
                .clickable { onNavigateToAddNote(null) }
                .padding(8.dp)
        )
    }
}