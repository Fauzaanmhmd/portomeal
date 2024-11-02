package com.example.masakapa.uploadfilewithprogressbar

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.masakapa.ui.theme.KtorUploadFileWithProgressBarTheme
import kotlin.math.roundToInt


@Composable
fun UploadFileScreen() {
    val context = LocalContext.current
    val httpClientInstance = HttpClient()
    KtorUploadFileWithProgressBarTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val viewModel = viewModel {
                UploadFileViewModel(
                    repository = FileRepository(
                        httpClient = httpClientInstance.client,
                        fileReader = FileReader(
                            context = context
                        )
                    )
                )
            }
            val state = viewModel.state

            val filePickerLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { contentUri ->
                contentUri?.let {
                    viewModel.uploadFile(contentUri)
                }
            }

            LaunchedEffect(key1 = state.errorMessage) {
                state.errorMessage?.let {
                    Toast.makeText(
                        context,
                        state.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            LaunchedEffect(key1 = state.isUploadComplete) {
                if (state.isUploadComplete) {
                    Toast.makeText(
                        context,
                        "Upload complete!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when {
                    !state.isUploading -> {
                        Button(onClick = {
                            filePickerLauncher.launch("*/*")
                        }) {
                            Text(text = "Pick a file")
                        }
                    }

                    else -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val animatedProgress by animateFloatAsState(
                                targetValue = state.progress,
                                animationSpec = tween(durationMillis = 100),
                                label = "File upload progress bar"
                            )
                            LinearProgressIndicator(
                                progress = animatedProgress,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .height(16.dp),
                                color = Color.Red,
                                trackColor = Color.Gray
                            )
                            Text(
                                text = "${(state.progress * 100).roundToInt()}%"
                            )
                            Button(onClick = {
                                viewModel.cancelUpload()
                            }) {
                                Text(text = "Cancel upload")
                            }
                        }
                    }
                }
            }
        }
    }
}