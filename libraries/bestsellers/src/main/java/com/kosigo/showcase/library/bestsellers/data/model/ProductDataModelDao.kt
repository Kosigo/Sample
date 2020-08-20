package com.kosigo.showcase.library.bestsellers.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber


@Dao
interface ProductDataModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productDataModel: ProductDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<ProductDataModel>)

    @Delete
    suspend fun delete(productDataModel: ProductDataModel)

    @Query("DELETE FROM ProductDataModel")
    suspend fun deleteAll()

    @Query("SELECT * FROM ProductDataModel")
    suspend fun getList() : List<ProductDataModel>

    @Transaction
    @Query("SELECT * FROM ProductDataModel")
    fun getListFlow(): Flow<List<ProductDataModel>>

    @Transaction
    @Query("SELECT * FROM ProductDataModel WHERE `name` = :name")
    fun getItemFlow(name: String): Flow<ProductDataModel>

    @Transaction
    @Query("SELECT * FROM ProductDataModel WHERE `name` = :name")
    fun getItem(name: String): ProductDataModel?

    @Transaction
    suspend fun deleteAllAndInsert(listProduct: List<ProductDataModel>) {
        Timber.i("DELETING & INSERTING DATA")
        deleteAll()
        listProduct.forEach {  insert(it) }
    }

}
