package com.bunbeauty.photogallery

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Photo(
    @SerializedName("albumId")
    @Expose
    val albumId: Int = 0,
    @SerializedName("id")
    @Expose
    val id: Int = 0,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String? = null
) {}