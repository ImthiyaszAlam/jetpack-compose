package com.imthiyas.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.imthiyas.newsapp.domain.manager.LocalUserManager
import com.imthiyas.newsapp.util.Constants
import com.imthiyas.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {
    override suspend fun saveAppEntry() {

    }

    override fun readAppEntry(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object preferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}