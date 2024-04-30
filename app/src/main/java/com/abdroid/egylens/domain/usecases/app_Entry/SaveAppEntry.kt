package com.abdroid.egylens.domain.usecases.app_Entry

import com.abdroid.egylens.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}