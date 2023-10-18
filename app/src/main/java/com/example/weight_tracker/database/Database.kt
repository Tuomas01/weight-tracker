package com.example.weight_tracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weight_tracker.MyApp

@Database(entities = [Weight::class], version = 2)
abstract class WeightDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: WeightDatabase? = null

        fun getDatabase(context: Context): WeightDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    MyApp.appContext,
                    WeightDatabase::class.java,
                    "weight_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}