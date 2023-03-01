package com.pek.cook.sealed

import com.pek.cook.model.Food

sealed class DataState {
    class Success(val data: MutableList<Food>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}
