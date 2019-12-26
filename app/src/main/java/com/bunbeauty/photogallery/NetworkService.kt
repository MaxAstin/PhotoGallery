package com.bunbeauty.photogallery

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    fun getJsonApi(): JsonApi {
        return retrofit.create(JsonApi::class.java)
    }

    companion object {
        private var BASE_URL = "https://jsonplaceholder.typicode.com"

        private var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private var mInstance: NetworkService? = null

        @JvmStatic
        fun getInstance(): NetworkService {
            if (mInstance == null) {
                mInstance = NetworkService()
            }
            return mInstance as NetworkService
        }

        @JvmStatic
        fun changeBaseUrl(newUrl: String) {
            BASE_URL = newUrl

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}