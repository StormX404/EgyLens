package com.abdroid.egylens.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readAppEntry() : Flow<Boolean>

    suspend fun saveIsBookmarked(name: String, isBookmarked: Boolean)

    fun readIsBookmarked(name : String): Flow<Boolean>

}