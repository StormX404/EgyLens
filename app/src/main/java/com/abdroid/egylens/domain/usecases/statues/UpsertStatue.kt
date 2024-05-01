package com.abdroid.egylens.domain.usecases.statues

import com.abdroid.egylens.data.local.StatueDao
import com.abdroid.egylens.domain.model.Statue
import javax.inject.Inject

class UpsertStatue @Inject constructor(
    private val statueDao: StatueDao
) {

    suspend operator fun invoke(statue: Statue){
        statueDao.insertStatue(statue = statue)
    }

}