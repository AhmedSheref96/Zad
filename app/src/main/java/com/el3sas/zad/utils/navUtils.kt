package com.el3sas.zad.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
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

    override fun parseValue(value: String): T = json.decodeFromString(serializer, value)

    override fun serializeAsValue(value: T): String =
        if (value == null) {
            ""
        } else {
            json.encodeToString(serializer, value)
        }

    override fun put(
        bundle: Bundle,
        key: String,
        value: T,
    ) {
        if (value == null) {
            bundle.putParcelable(key, null)
        } else {
            bundle.putParcelable(key, value)
        }
    }
}
