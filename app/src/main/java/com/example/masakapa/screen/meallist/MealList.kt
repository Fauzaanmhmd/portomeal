package com.example.masakapa.screen.meallist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.plcoding.typesafecomposenavigation.R


@Composable
fun MealListScreen(
    strCategory: String,
    onClick: (strCategory: String) -> Unit,
    viewModel: MealListViewModel = hiltViewModel()
) {

    val meals by viewModel.mealList.collectAsState()

    var query by remember {
        mutableStateOf(TextFieldValue(""))
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getMealList(strCategory)
    }

    Column {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.filterList(it.text)
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

            Image(
                painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
                    .padding(16.dp)
                    .clickable {
                        viewModel.reverseList()
                    }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // You can change the number of columns as needed
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
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
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