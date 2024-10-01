package com.example.masakapa.screen.mealsearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.plcoding.typesafecomposenavigation.R

@Composable
fun MealSearchScreen(
    onClick: (strCategory: String) -> Unit,
    viewModel: MealSearchViewModel = hiltViewModel()
) {
    val meals by viewModel.mealDetail.collectAsState()
    var query by remember {
        mutableStateOf(TextFieldValue(""))
    }


    Column {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.getMealSearch(query.text)
                },
                label = {
                    Text(
                        "Search"
                    )
                },
                modifier = Modifier
                    .weight(1F)
                    .padding(8.dp)
            )
        }

        if (meals.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = R.drawable.ic_empty,
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(meals.size) { index ->
                    val meal = meals[index]
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .height(250.dp)
                            .clickable { onClick(meal.idMeal) }
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            AsyncImage(
                                model = meal.strMealThumb,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = meal.strMeal,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }


        }


    }
}