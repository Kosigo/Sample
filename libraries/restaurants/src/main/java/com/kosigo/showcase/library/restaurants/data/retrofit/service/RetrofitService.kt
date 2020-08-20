package com.kosigo.showcase.library.restaurants.data.retrofit.service

import com.kosigo.showcase.library.restaurants.data.model.RestaurantDataModel
import com.kosigo.showcase.library.restaurants.data.retrofit.response.GetAlbumInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface RetrofitService {
    @GET("api/v1/restaurants")
    suspend fun searchAlbumAsync(): List<RestaurantDataModel>

    @POST("./?method=album.getInfo")
    suspend fun getAlbumInfoAsync(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("mbid") mbId: String?
    ): GetAlbumInfoResponse?

}
