package com.abdroid.egylens.domain.repository

import com.abdroid.egylens.domain.model.Statue
import kotlinx.coroutines.flow.Flow

interface StatuesRepository {

    /*fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>*/

    suspend fun insertStatue(statue: Statue)

    suspend fun deleteStatue(statue: Statue)

    fun getAllStatues(): Flow<List<Statue>>

    suspend fun getStatue(name: String): Statue?

}