package com.abdroid.egylens.domain.usecases.statues

import com.abdroid.egylens.data.local.StatueDao
import com.abdroid.egylens.domain.model.Statue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedStatues @Inject constructor(
    private val statueDao: StatueDao
) {

    operator fun invoke(): Flow<List<Statue>>{
        return statueDao.getAllStatues()
    }

}