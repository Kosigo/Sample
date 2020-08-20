package com.kosigo.showcase.library.restaurants.data.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        StringKeyValuePair::class,
        RestaurantDataModel::class
    ], version = 1
)
abstract class ShopsDatabase : RoomDatabase() {
    abstract fun keyValueDao(): StringKeyValueDao
    abstract fun restaurantDataModelDao(): RestaurantDataModelDao

    companion object{
        const val DATABASE_NAME = "restaurants"
    }
}
