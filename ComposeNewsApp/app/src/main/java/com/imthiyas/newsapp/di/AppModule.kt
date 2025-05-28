package com.imthiyas.newsapp.di

import android.app.Application
import com.imthiyas.newsapp.data.manager.LocalUserManagerImpl
import com.imthiyas.newsapp.domain.manager.LocalUserManager
import com.imthiyas.newsapp.domain.usecases.AppEntryUseCases
import com.imthiyas.newsapp.domain.usecases.ReadAppEntry
import com.imthiyas.newsapp.domain.usecases.SaveAppEntry
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
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}