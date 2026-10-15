package com.embel.shop.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.embel.shop.data.model.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

private val Context.cartDataStore: DataStore<Preferences> by preferencesDataStore(name = "cart_prefs")

@Singleton
class CartDataStore @Inject constructor(
    private val context: Context
) {
    private val cartKey = stringPreferencesKey("cart_list")

    val cartItems: Flow<List<CartItem>> = context.cartDataStore.data.map { preferences ->
        val json = preferences[cartKey] ?: "[]"
        try {
            Json.decodeFromString<List<CartItem>>(json)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun saveCart(items: List<CartItem>) {
        context.cartDataStore.edit { preferences ->
            preferences[cartKey] = Json.encodeToString(items)
        }
    }
}
