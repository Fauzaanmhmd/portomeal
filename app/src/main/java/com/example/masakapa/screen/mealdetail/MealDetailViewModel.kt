package com.example.masakapa.screen.mealdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masakapa.repository.MealRepository
import com.example.masakapa.service.Category
import com.example.masakapa.service.FilterCategory
import com.example.masakapa.service.MealDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {
    private val _mealDetail = MutableStateFlow<List<MealDetail>>(emptyList())
    val mealDetail: StateFlow<List<MealDetail>> = _mealDetail

    fun getMealDetail(idMeal: String) {
        viewModelScope.launch {
            repository.getMealDetail(idMeal).collect {
                _mealDetail.value = it?.meals.orEmpty()
            }
        }
    }
}
