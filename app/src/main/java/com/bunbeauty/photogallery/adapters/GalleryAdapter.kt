package com.bunbeauty.photogallery.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bunbeauty.photogallery.adapters.elements.AlbumElement
import com.bunbeauty.photogallery.R
import com.bunbeauty.photogallery.entities.Album

class GalleryAdapter(private val albumsList: ArrayList<Album>) :
    RecyclerView.Adapter<GalleryAdapter.AlbumsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AlbumsViewHolder {
        val layoutIdForListItem = R.layout.album_element
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view = layoutInflater.inflate(layoutIdForListItem, viewGroup, false)

        return GalleryAdapter(albumsList)
            .AlbumsViewHolder(view, viewGroup.context)
    }

    override fun onBindViewHolder(albumsViewHolder: AlbumsViewHolder, i: Int) {
        albumsViewHolder.bind(albumsList[i])
    }

    override fun getItemCount() = albumsList.size

    inner class AlbumsViewHolder(private val view: View, private val context: Context) :
        RecyclerView.ViewHolder(view) {
        fun bind(album: Album) {
            val albumElement = AlbumElement(context)
            albumElement.createElement(album, view)
        }
    }
}