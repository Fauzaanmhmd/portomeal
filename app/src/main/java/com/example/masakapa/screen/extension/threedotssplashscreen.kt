package com.example.masakapa.screen.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ImageSliderWithIndicator(
    onNavigate: () -> Unit,
    images: List<Int>,
) {
    val pagerState = rememberPagerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pager untuk swipe gambar
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Mengisi ruang yang tersisa
        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            )
        }

        // Tombol GET STARTED
        if (pagerState.currentPage == images.size - 1) { // Tampilkan hanya di halaman terakhir
            Button(
                onClick = onNavigate,
                colors = ButtonDefaults.buttonColors(Color.Cyan),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
            ) {
                Text(text = "GET STARTED")
            }
        }

        // Indikator titik-titik
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = Color.Gray,
            indicatorShape = CircleShape,
            indicatorWidth = 8.dp,
            spacing = 8.dp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}