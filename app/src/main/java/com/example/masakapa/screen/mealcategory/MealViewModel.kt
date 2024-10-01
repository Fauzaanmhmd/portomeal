package com.example.masakapa.screen.mealcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masakapa.repository.MealRepository
import com.example.masakapa.service.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories
    var baseCategory: List<Category> = emptyList()

    fun getCategories() {
        viewModelScope.launch {
            repository.getCategories().collect {
                _categories.value = it?.categories.orEmpty()
                baseCategory = it?.categories.orEmpty()
            }
        }
    }

    fun filterCategory(query: String) {
        if (query.isEmpty()) {
            _categories.value = baseCategory
        } else {
            _categories.value = baseCategory.filter {
                it.strCategory.lowercase().contains(query.lowercase())
            }
        }
    }
}
