package com.example.weight_tracker

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weight_tracker.database.Weight
import com.example.weight_tracker.database.WeightDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeightViewModel(application: Application) : AndroidViewModel(application) {
    private val db = WeightDatabase.getDatabase(application)
    private val weightDao = db.weightDao()
    var openPopUp = mutableStateOf(false)

    fun getAllWeights(): LiveData<List<Weight>> {
        val weights = weightDao.getAll()
        return weights.asLiveData()
    }

    fun updateWeight(weight: Int, date: Long, id: Int) {
        viewModelScope.launch { weightDao.updateSelectedWeight(weight, date, id) }
    }

    fun addWeight(weight: Int, date: Long) {
        val newWeight = Weight(0, weight, date)
        viewModelScope.launch { weightDao.addNewWeight(newWeight) }
    }

    fun isOpen() {
        if (!openPopUp.value) {
            openPopUp.value = true
        } else {
            openPopUp.value = false
        }
    }
}