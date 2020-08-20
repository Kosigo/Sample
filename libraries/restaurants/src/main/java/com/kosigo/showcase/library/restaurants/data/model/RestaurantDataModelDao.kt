package com.kosigo.showcase.library.restaurants.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber


@Dao
interface RestaurantDataModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(restaurantDataModel: RestaurantDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<RestaurantDataModel>)

    @Delete
    suspend fun delete(restaurantDataModel: RestaurantDataModel)

    @Query("DELETE FROM RestaurantDataModel")
    suspend fun deleteAll()

    @Query("SELECT * FROM RestaurantDataModel")
    suspend fun getList() : List<RestaurantDataModel>

    @Transaction
    @Query("SELECT * FROM RestaurantDataModel")
    fun getListFlow(): Flow<List<RestaurantDataModel>>


    @Transaction
    suspend fun deleteAllAndInsert(listProduct: List<RestaurantDataModel>) {
        Timber.i("DELETING & INSERTING DATA")
        deleteAll()
        listProduct.forEach {  insert(it) }
    }

}
