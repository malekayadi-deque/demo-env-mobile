package com.embel.shop.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

@Singleton
class PreferencesDataStore @Inject constructor(
    private val context: Context
) {
    private val cookieAcceptedKey = booleanPreferencesKey("cookie_accepted")

    val isCookieAccepted: Flow<Boolean> = context.preferencesDataStore.data.map { preferences ->
        preferences[cookieAcceptedKey] ?: false
    }

    suspend fun setCookieAccepted(accepted: Boolean) {
        context.preferencesDataStore.edit { preferences ->
            preferences[cookieAcceptedKey] = accepted
        }
    }
}
