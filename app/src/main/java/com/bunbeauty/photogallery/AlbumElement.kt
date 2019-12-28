package com.bunbeauty.photogallery

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bunbeauty.photogallery.entities.Album
import com.squareup.picasso.Picasso

class AlbumElement(context: Context) : View.OnClickListener {

    private lateinit var album: Album

    fun createElement(album: Album, view: View) {
        this.album = album

        val nameText: TextView = view.findViewById(R.id.titleAlbumText)
        nameText.text = album.title

        val imageViews = ArrayList<ImageView>()
        imageViews.add(view.findViewById(R.id.mainAlbumImage))
        imageViews.add(view.findViewById(R.id.secondAlbumImage))
        imageViews.add(view.findViewById(R.id.thirdAlbumImage))
        setImage(imageViews)

        val mainLayout: LinearLayout = view.findViewById(R.id.mainAlbumLayout)
        mainLayout.setOnClickListener(this)
    }

    private fun setImage(imageViews: ArrayList<ImageView>) {
        for ((i, imageView) in imageViews.withIndex()) {
            if (album.firstImages.size > i) {
                Picasso.get()
                    .load(album.firstImages[i])
                    .resize(100, 100)
                    .centerCrop()
                    .into(imageView)
            }
        }
    }

    override fun onClick(view: View) {

    }
}