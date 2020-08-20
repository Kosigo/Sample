package com.kosigo.showcase.library.bestsellers.data.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        StringKeyValuePair::class,
        ProductDataModel::class
    ], version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun keyValueDao(): StringKeyValueDao
    abstract fun restaurantDataModelDao(): ProductDataModelDao

    companion object{
        const val DATABASE_NAME = "Bestsellers"
    }
}
