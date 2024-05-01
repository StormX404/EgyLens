package com.abdroid.egylens.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdroid.egylens.domain.model.Statue

@Database(entities = [Statue::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statuteDao(): StatueDao
}
