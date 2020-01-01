package com.bunbeauty.photogallery.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bunbeauty.photogallery.NetworkService
import com.bunbeauty.photogallery.R
import com.bunbeauty.photogallery.adapters.PhotoAdapter
import com.bunbeauty.photogallery.entities.Album
import com.bunbeauty.photogallery.entities.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoSliderActivity: AppCompatActivity() {

    private lateinit var pager: ViewPager
    private val photosList = ArrayList<Photo>()
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_slider)

        pager = findViewById(R.id.photoPager)
        photoAdapter = PhotoAdapter(photosList, this)
        pager.adapter = photoAdapter
        getPhotosFromAlbum()
    }

    private fun getPhotoNumber() = intent.getIntExtra(Photo.PHOTO_NUMBER, 0)

    private fun getPhotosFromAlbum() {
        val albumId = intent.getIntExtra(Album.ALBUM_ID, 0)

        NetworkService.getInstance()
            .getJsonApi()
            .getPhotosByAlbumId(albumId)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    for ((i, photo) in response.body()!!.withIndex()) {
                        photosList.add(photo)
                        photoAdapter.notifyDataSetChanged()

                        if(i == getPhotoNumber()) {
                            pager.setCurrentItem(getPhotoNumber(), false)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {}
            })
    }
}