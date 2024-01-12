package com.example.weight_tracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface WeightDao {
    @Query("SELECT * FROM Weight")
    fun getAll(): Flow<List<Weight>>

    @Query("UPDATE Weight SET weight = :weight, date = :date WHERE id = :id")
    suspend fun updateSelectedWeight(weight: Int, date: Date, id: Int)

    @Insert
    suspend fun addNewWeight(weight: Weight)

    @Delete
    fun delete(weight: Weight)
}