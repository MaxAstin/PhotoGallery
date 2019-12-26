package com.bunbeauty.photogallery.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Album(
    @SerializedName("userId")
    @Expose
    val userId: Int = 0,
    @SerializedName("id")
    @Expose
    val id: Int = 0,
    @SerializedName("title")
    @Expose
    val title: String = "Альбом №$id"
) {}