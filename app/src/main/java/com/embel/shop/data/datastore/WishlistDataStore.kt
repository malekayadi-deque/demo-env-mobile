package com.embel.shop.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

private val Context.wishlistDataStore: DataStore<Preferences> by preferencesDataStore(name = "wishlist_prefs")

@Singleton
class WishlistDataStore @Inject constructor(
    private val context: Context
) {
    private val wishlistKey = stringPreferencesKey("wishlist_ids")

    val wishlistIds: Flow<List<Int>> = context.wishlistDataStore.data.map { preferences ->
        val json = preferences[wishlistKey] ?: "[]"
        try {
            Json.decodeFromString<List<Int>>(json)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun saveWishlist(ids: List<Int>) {
        context.wishlistDataStore.edit { preferences ->
            preferences[wishlistKey] = Json.encodeToString(ids)
        }
    }
}
