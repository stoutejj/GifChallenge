package com.jamal.giffy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jamal.giffy.R
import com.jamal.giffy.data.model.GiffyImage
import kotlinx.android.synthetic.main.view_holder_giff.view.*

class GifAdapter(
    private val images: MutableList<GiffyImage>,
    private val onImageClick: (GiffyImage) -> Unit
) :
    RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_giff, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], onImageClick)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun updateImages(images: List<GiffyImage>) {
        this.images.clear()
        this.images.addAll(images)
        this.notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(giffyImage: GiffyImage, onImageClick: (GiffyImage) -> Unit) {
            itemView.setOnClickListener {
                onImageClick.invoke(giffyImage)
            }
            Glide.with(itemView).load(giffyImage.images.downSized.url).into(itemView.image)
        }

    }
}
