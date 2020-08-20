package com.kosigo.showcase.library.reviews.data.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber


@Dao
interface ReviewsDataModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reviewsDataModel: ReviewsDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<ReviewsDataModel>)

    @Delete
    suspend fun delete(reviewsDataModel: ReviewsDataModel)

    @Query("DELETE FROM ReviewsDataModel")
    suspend fun deleteAll()

    @Query("SELECT * FROM ReviewsDataModel")
    suspend fun getList() : List<ReviewsDataModel>

    @Transaction
    @Query("SELECT * FROM ReviewsDataModel")
    fun getListFlow(): Flow<List<ReviewsDataModel>>

    @Transaction
    @Query("SELECT * FROM ReviewsDataModel WHERE `message` = :name")
    fun getItemFlow(name: String): Flow<ReviewsDataModel>

    @Transaction
    @Query("SELECT * FROM ReviewsDataModel WHERE `message` = :name")
    fun getItem(name: String): ReviewsDataModel?

    @Transaction
    suspend fun deleteAllAndInsert(listReviews: List<ReviewsDataModel>) {
        Timber.i("DELETING & INSERTING DATA")
        deleteAll()
        listReviews.forEach {  insert(it) }
    }

}
