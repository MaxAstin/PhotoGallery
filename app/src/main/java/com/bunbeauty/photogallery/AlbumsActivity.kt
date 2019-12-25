package com.bunbeauty.photogallery

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlbumsActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_main)

        //textView = findViewById(R.id.testAlbumsActivityText)
        imageView = findViewById(R.id.testAlbumsActivityImage)

        getAlbums()
    }

    private fun getAlbums() {
        NetworkService.getInstance()
            .getJsonApi()
            .getPhotosByAlbumId(12)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    NetworkService.changeBaseUrl(NetworkService.PHOTO_URL)
                    val photos = response.body()
                    getPhoto(photos!![0].url!!)
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                }
            })
    }

    fun getPhoto(url: String) {
        val parms = url.removeRange(0..NetworkService.PHOTO_URL.length)
        val firstParm = parms.split("/")[0]
        val secondParm = parms.split("/")[1]

        NetworkService.getInstance()
            .getJsonApi()
            .getPhoto(firstParm, secondParm)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val url = call.request().url()
                    val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                    imageView.setImageBitmap(bitmap)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                }
            })
    }
}
