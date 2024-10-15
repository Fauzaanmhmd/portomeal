package com.example.masakapa.navigation

sealed class Screen(val route: String) {
    data object MealSplash: Screen("mealSplash")
    data object MealCategory: Screen("mealCategory")
    data object MealFavorite: Screen("mealFavorite")
    data object NoteList: Screen("noteList")

    data object MealDetail: Screen("detail/{params}") {
        fun createRoute(params: String): String {
            return "detail/$params"
        }
    }

    data object MealList: Screen("category/{params}") {
        fun createRoute(params: String): String {
            return "category/$params"
        }
    }

    data object MealSearch: Screen("mealSearch")
    data object AddNote: Screen("addNote/{params}") {
        fun createRoute(params: Int) : String {
            return "addNote/$params"
        }
    }

}