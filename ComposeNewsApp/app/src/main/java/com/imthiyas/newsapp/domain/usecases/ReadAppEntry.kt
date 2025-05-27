package com.imthiyas.newsapp.domain.usecases

import com.imthiyas.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(): Flow<Boolean> {
       return localUserManager.readAppEntry()
    }
}