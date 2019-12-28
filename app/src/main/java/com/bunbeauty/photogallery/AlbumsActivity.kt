package com.bunbeauty.photogallery

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bunbeauty.photogallery.entities.Album
import com.bunbeauty.photogallery.entities.Photo
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlbumsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val albumsList = ArrayList<Album>()
    private lateinit var albumsAdapter: AlbumsAdapter

    private var countAlbums = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        recyclerView = findViewById(R.id.albumsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getAlbums()
        albumsAdapter = AlbumsAdapter(albumsList)
        recyclerView.adapter = albumsAdapter
    }

    private fun getAlbums() {
        NetworkService.getInstance()
            .getJsonApi()
            .getAlbums()
            .enqueue(object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    countAlbums = response.body()!!.size
                    for (album in response.body()!!) {
                        getPhotosFromAlbum(album)
                    }
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {}
            })
    }

    private fun getPhotosFromAlbum(album: Album) {
        NetworkService.getInstance()
            .getJsonApi()
            .getPhotosByAlbumId(album.id)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    for ((i, photo) in response.body()!!.withIndex()) {
                        album.firstImages.add(photo.url!!)
                        if (i == 2) break
                    }
                    albumsList.add(album)
                    albumsAdapter.notifyItemInserted(albumsList.size - 1)
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {}
            })
    }
}
