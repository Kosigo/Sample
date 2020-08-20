package com.kosigo.showcase.library.reviews.data.retrofit.service

import com.kosigo.showcase.library.reviews.data.model.ReviewsDataModel
import retrofit2.http.GET

internal interface RetrofitService {
    @GET("api/v1/reviews")
    suspend fun searchAlbumAsync(): List<ReviewsDataModel>
}
