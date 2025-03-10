package com.el3sas.zad.utils

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

inline fun <reified T : Parcelable?> navType(
    isNullableAllowed: Boolean = true,
    json: Json = Json,
    serializer: KSerializer<T>,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(
        bundle: Bundle,
        key: String,
    ): T? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = json.decodeFromString(serializer, Uri.decode(value)) // فك التشفير قبل التحويل

    override fun serializeAsValue(value: T): String =
        if (value == null) {
            ""
        } else {
            Uri.encode(json.encodeToString(serializer, value)) // تشفير القيم قبل تمريرها
        }

    override fun put(
        bundle: Bundle,
        key: String,
        value: T,
    ) {
        bundle.putParcelable(key, value)
    }
}

inline fun <reified T : Parcelable?> navType(
    isNullableAllowed: Boolean = true,
    gson: Gson = Gson(),
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(
        bundle: Bundle,
        key: String,
    ): T? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = gson.fromJson(Uri.decode(value), T::class.java) // فك التشفير قبل التحويل

    override fun serializeAsValue(value: T): String =
        if (value == null) {
            ""
        } else {
            Uri.encode(gson.toJson(value)) // تشفير القيم قبل تمريرها
        }

    override fun put(
        bundle: Bundle,
        key: String,
        value: T,
    ) {
        bundle.putParcelable(key, value)
    }
}
