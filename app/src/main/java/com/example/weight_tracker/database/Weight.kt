package com.example.weight_tracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weight (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val weight: Int?,
    val date: Long?,
)