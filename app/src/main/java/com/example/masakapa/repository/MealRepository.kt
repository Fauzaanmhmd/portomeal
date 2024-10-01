package com.example.masakapa.repository

import com.example.masakapa.service.CategoryResponse
import com.example.masakapa.service.FilterCategoryResponse
import com.example.masakapa.service.MealApiService
import com.example.masakapa.service.MealDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MealRepository @Inject constructor(private val apiService: MealApiService) {
    suspend fun getCategories(): Flow<CategoryResponse?> {
        return flowOf(apiService.getCategories())
    }

    suspend fun getMealList(strCategory: String): Flow<FilterCategoryResponse?> {
        return flowOf(apiService.getCategoriesFilter(strCategory))
    }

    suspend fun getMealDetail(idMeal: String): Flow<MealDetailResponse?> {
        return flowOf(apiService.getCategoryDetail(idMeal))
    }

    suspend fun getMealSearch(name: String): Flow<MealDetailResponse?> {
        return flowOf(apiService.getCategorySearch(name))
    }
}
