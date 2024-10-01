package com.example.masakapa.screen.mealdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage


@Composable
fun MealDetail(idMeal: String, viewModel: MealDetailViewModel = hiltViewModel()) {

    val meals by viewModel.mealDetail.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getMealDetail(idMeal)
    }

    meals.firstOrNull()?.let { meal ->
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = null,
                    modifier = Modifier
                        .size(128.dp)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = meal.strMeal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = meal.strCategory,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}