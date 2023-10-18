package com.example.weight_tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weight_tracker.database.Weight
import com.example.weight_tracker.database.WeightDatabase
import kotlinx.coroutines.launch

class WeightViewModel(application: Application) : AndroidViewModel(application) {
    private val db = WeightDatabase.getDatabase(application)
    private val weightDao = db.weightDao()

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
}