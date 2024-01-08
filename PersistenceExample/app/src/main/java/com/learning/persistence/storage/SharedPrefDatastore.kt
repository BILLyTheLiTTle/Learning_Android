package com.learning.persistence.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SharedPrefDatastore(ctx: Context) {
    private val dataSource = ctx.userPreferencesDataStore
    suspend fun save(user: User) {
        dataSource.edit { preferences ->
            preferences[USER_FIRST_NAME] = user.firstname
            preferences[USER_LAST_NAME] = user.lastname
        }
    }

    fun retrieve(): Flow<User> = dataSource.data
        .map { preferences ->
            User(
                firstname = preferences[USER_FIRST_NAME] ?: "",
                lastname = preferences[USER_LAST_NAME] ?: ""
            )
        }

    suspend fun clearAll() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {
        val USER_FIRST_NAME = stringPreferencesKey("user_first_name")
        val USER_LAST_NAME = stringPreferencesKey("user_last_name")
    }
}

private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user"
)