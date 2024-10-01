package com.example.masakapa.screen.meallist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masakapa.repository.MealRepository
import com.example.masakapa.service.FilterCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {
    private val _mealList = MutableStateFlow<List<FilterCategory>>(emptyList())
    val mealList: StateFlow<List<FilterCategory>> = _mealList
    var baseMealist: List<FilterCategory> = emptyList()

    fun getMealList(strCategory: String) {
        viewModelScope.launch {
            repository.getMealList(strCategory).collect {
                _mealList.value = it?.meals.orEmpty()
                baseMealist = it?.meals.orEmpty()
            }
        }
    }

    fun filterList(query: String) {
        if (query.isEmpty()) {
            _mealList.value = baseMealist
        } else {
            _mealList.value = baseMealist.filter {
                it.strMeal.lowercase().contains(query.lowercase())
            }
        }
    }

    fun reverseList() {
        _mealList.value = _mealList.value.reversed()
    }
}
