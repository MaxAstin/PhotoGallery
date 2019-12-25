package com.bunbeauty.photogallery

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JsonApi {
    @GET("photos")
    fun getPhotosByAlbumId(@Query("albumId") id: Int): Call<List<Photo>>

    @GET("{firstPathParm}/{secondPathParm}")
    fun getPhoto(
        @Path("firstPathParm") firstPathParm: String,
        @Path("secondPathParm") secondPathParm: String
    ): Call<ResponseBody>

}