package com.imthiyas.newsapp.domain.usecases

import com.imthiyas.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun  invoke(){
        localUserManager.saveAppEntry()
    }
}