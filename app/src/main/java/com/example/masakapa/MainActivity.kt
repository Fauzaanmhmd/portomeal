package com.example.masakapa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.masakapa.navigation.Screen
import com.example.masakapa.screen.favorite.MealFavoriteScreen
import com.example.masakapa.screen.mealcategory.MealCategoryScreen
import com.example.masakapa.screen.mealdetail.MealDetail
import com.example.masakapa.screen.meallist.MealListScreen
import com.example.masakapa.screen.mealsearch.MealSearchScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootApp()
        }
    }
}

@Composable
fun RootApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.MealCategory.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.MealFavorite.route) {
                MealFavoriteScreen()
            }

            composable(Screen.MealCategory.route) {
                MealCategoryScreen(
                    onClick = {
                        navController.navigate(Screen.MealList.createRoute(it))
                    },
                    onNavigateToMealSearch = {
                        navController.navigate(Screen.MealSearch.route)
                    }
                )
            }

            composable(Screen.MealList.route, arguments = listOf(
                navArgument("params") {
                    type = NavType.StringType
                }
            )) { it ->
                MealListScreen(strCategory = it.arguments?.getString("params").toString(), {
                    navController.navigate(Screen.MealDetail.createRoute(it))
                })
            }

            composable(Screen.MealDetail.route, arguments = listOf(
                navArgument("params") {
                    type = NavType.StringType
                }
            )) {
                MealDetail(idMeal = it.arguments?.getString("params").toString())
            }

            composable(Screen.MealSearch.route) {
                MealSearchScreen(onClick = {
                    navController.navigate(Screen.MealDetail.createRoute(it))
                })
            }
        }
    }
}
