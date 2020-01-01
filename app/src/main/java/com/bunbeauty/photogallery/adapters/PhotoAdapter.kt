package com.bunbeauty.photogallery.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bunbeauty.photogallery.R
import com.bunbeauty.photogallery.entities.Photo
import com.squareup.picasso.Picasso

class PhotoAdapter(private val photosList: ArrayList<Photo>, private val context: Context): PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        container.addView(imageView)
        setPhoto(imageView, photosList[position].url)

        return imageView
    }

    private fun setPhoto(imageView: ImageView, url: String?) {
        Picasso.get()
            .load(url)
            .resize(100, 100)
            .centerCrop()
            .into(imageView)
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as ImageView)
    }

    override fun getCount() = photosList.size
}