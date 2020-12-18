package com.example.yapai.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.domain.Photo
import com.example.yapai.R
import com.example.yapai.databinding.PhotoItemBinding
import com.example.yapai.presentation.utils.MakeUrl

class PhotoAdapter(
    val context: Context,
    var photos: List<Photo>
):RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: PhotoItemBinding = PhotoItemBinding.bind(itemView)
        fun bind(item: Photo) {
            val option: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.image_not_found)

            Glide.with(context)
                .load(MakeUrl(item).invoke())
                .apply(option)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun addAll(photo: List<Photo>?) {
        if (photo != null) {
            photos=photo
            notifyDataSetChanged()
        }
    }
}