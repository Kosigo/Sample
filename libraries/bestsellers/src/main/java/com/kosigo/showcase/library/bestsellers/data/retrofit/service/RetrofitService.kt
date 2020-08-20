package com.kosigo.showcase.library.bestsellers.data.retrofit.service

import com.kosigo.showcase.library.bestsellers.data.model.ProductDataModel
import retrofit2.http.GET

internal interface RetrofitService {
    @GET("api/v1/hits")
    suspend fun searchAlbumAsync(): List<ProductDataModel>
}
