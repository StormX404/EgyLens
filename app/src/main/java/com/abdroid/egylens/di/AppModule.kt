package com.abdroid.egylens.di

import android.app.Application
import com.abdroid.egylens.data.manager.AuthRepositoryImpl
import com.abdroid.egylens.data.manager.LocalUserManagerImp
import com.abdroid.egylens.domain.manager.AuthRepository
import com.abdroid.egylens.domain.manager.LocalUserManager
import com.abdroid.egylens.domain.usecases.app_Entry.AppEntryUseCases
import com.abdroid.egylens.domain.usecases.app_Entry.ReadAppEntry
import com.abdroid.egylens.domain.usecases.app_Entry.SaveAppEntry
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImp(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
    localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun providesFirebaseAuth()  = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}