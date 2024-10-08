package com.example.masakapa.service


import retrofit2.http.GET
import retrofit2.http.Query

data class CategoryResponse(val categories: List<Category>)
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class FilterCategoryResponse(val meals: List<FilterCategory>)
data class FilterCategory(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String,
)

data class MealDetailResponse(
    val meals: List<MealDetail>,
)

data class MealDetail(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: Any?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: Any?,
    val strYoutube: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient20: String,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure20: String,
    val strSource: String,
    val strImageSource: Any?,
    val strCreativeCommonsConfirmed: Any?,
    val dateModified: Any?,
)



interface MealApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getCategoriesFilter(@Query("c") strCategory: String): FilterCategoryResponse

    @GET("lookup.php")
    suspend fun getCategoryDetail(@Query("i") idMeal: String): MealDetailResponse

    @GET("search.php")
    suspend fun getCategorySearch(@Query("s") name: String): MealDetailResponse
}
