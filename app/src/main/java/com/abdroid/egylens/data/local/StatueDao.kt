package com.abdroid.egylens.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdroid.egylens.domain.model.Statue
import kotlinx.coroutines.flow.Flow

@Dao
interface StatueDao {

    @Query("SELECT * FROM Statue")
    fun getAllStatues(): Flow<List<Statue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatue(statue: Statue)

    @Delete
    suspend fun deleteStatue(statue: Statue)

    @Query("SELECT * FROM Statue WHERE name=:name")
    suspend fun getStatue(name: String): Statue?
}