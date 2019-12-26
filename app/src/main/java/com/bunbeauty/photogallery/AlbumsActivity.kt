package com.bunbeauty.photogallery

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bunbeauty.photogallery.entities.Album
import com.bunbeauty.photogallery.entities.Photo
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlbumsActivity : AppCompatActivity() {

    private lateinit var albumsLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_main)

        albumsLayout = findViewById(R.id.albumsAlbumsActivityLayout)

        getAlbums()
    }

    private fun getAlbums() {
        NetworkService.getInstance()
            .getJsonApi()
            .getAlbums()
            .enqueue(object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    for (album in response.body()!!) {
                        getPhotosFromAlbum(album.id)
                    }
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                }
            })
    }

    private fun getPhotosFromAlbum(albumId: Int) {
        /*val width = resources.getDimensionPixelSize(R.dimen.photo_width)
        val height = resources.getDimensionPixelSize(R.dimen.photo_height)*/
        NetworkService.getInstance()
            .getJsonApi()
            .getPhotosByAlbumId(albumId)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    val imageView = ImageView(this@AlbumsActivity)
                    albumsLayout.addView(imageView)

                    Picasso.get()
                        .load(response.body()!![0].url!!)
                        .resize(100, 100)
                        .centerCrop()
                        .into(imageView)
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                }
            })
    }
}
