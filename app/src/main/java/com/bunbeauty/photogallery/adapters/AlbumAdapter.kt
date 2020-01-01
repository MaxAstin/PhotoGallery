package com.bunbeauty.photogallery.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bunbeauty.photogallery.R
import com.bunbeauty.photogallery.activities.PhotoSliderActivity
import com.bunbeauty.photogallery.activities.PhotosActivity
import com.bunbeauty.photogallery.entities.Album
import com.bunbeauty.photogallery.entities.Photo
import com.squareup.picasso.Picasso


class AlbumAdapter(
    private val photos: ArrayList<Photo>,
    private val context: Context,
    private val albumId: Int
) :
    BaseAdapter(), View.OnClickListener {

    override fun onClick(view: View) {
        val intent = Intent(context, PhotoSliderActivity::class.java)
        intent.putExtra(Album.ALBUM_ID, albumId)
        intent.putExtra(Photo.PHOTO_NUMBER, view.tag as Int)
        context.startActivity(intent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            if (convertView == null) {
                val layoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                layoutInflater.inflate(R.layout.photo_element, parent, false)
            } else {
                convertView
            }

        val photo = getItem(position)
        val photoImage: ImageView = view.findViewById(R.id.photoImage)
        setImage(photoImage, photo.url)
        photoImage.tag = position
        photoImage.setOnClickListener(this)

        return view
    }

    private fun setImage(imageView: ImageView, url: String?) {
        if (url != null) {
            Picasso.get()
                .load(url)
                .resize(100, 100)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun getItem(position: Int): Photo {
        return photos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount() = photos.size
}