package com.kosigo.showcase.library.restaurants.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["key"])
data class StringKeyValuePair(
    val key: String, val value: String
)
