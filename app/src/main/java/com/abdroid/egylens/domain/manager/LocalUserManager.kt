package com.abdroid.egylens.domain.manager

import com.abdroid.egylens.presentation.onBoardingScreen.OnBoardingEvent
import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveAppEntry()

    fun readAppEntry() : Flow<Boolean>

}