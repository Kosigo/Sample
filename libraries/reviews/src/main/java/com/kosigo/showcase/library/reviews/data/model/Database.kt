package com.kosigo.showcase.library.reviews.data.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        StringKeyValuePair::class,
        ReviewsDataModel::class
    ], version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun keyValueDao(): StringKeyValueDao
    abstract fun restaurantDataModelDao(): ReviewsDataModelDao

    companion object{
        const val DATABASE_NAME = "Reviews"
    }
}
