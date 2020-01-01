package com.bunbeauty.photogallery.activities

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.bunbeauty.photogallery.NetworkService
import com.bunbeauty.photogallery.R
import com.bunbeauty.photogallery.adapters.AlbumAdapter
import com.bunbeauty.photogallery.entities.Album
import com.bunbeauty.photogallery.entities.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotosActivity : AppCompatActivity() {

    private val photosList = ArrayList<Photo>()
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val gridView: GridView = findViewById(R.id.photosGrid)
        albumAdapter = AlbumAdapter(photosList, this, getAlbumId())
        gridView.adapter = albumAdapter

        getPhotos()
    }

    private fun getPhotos() {
        val albumId = getAlbumId()

        getPhotosFromAlbum(albumId)
    }

    private fun getPhotosFromAlbum(albumId: Int) {
        NetworkService.getInstance()
            .getJsonApi()
            .getPhotosByAlbumId(albumId)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    for (photo in response.body()!!) {
                        photosList.add(photo)
                        albumAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {}
            })
    }

    private fun getAlbumId() = intent.getIntExtra(Album.ALBUM_ID, 0)
}
