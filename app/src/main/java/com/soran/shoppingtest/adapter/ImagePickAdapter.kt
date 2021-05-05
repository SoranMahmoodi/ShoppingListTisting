package com.soran.shoppingtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.soran.shoppingtest.R
import com.soran.shoppingtest.model.ImageResult
import com.soran.shoppingtest.utils.OnClickItemImagePick
import kotlinx.android.synthetic.main.item_image_pick.view.*
import javax.inject.Inject

class ImagePickAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<ImagePickAdapter.ImageViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var imageUrl: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnClickItemRv(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_pick, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imgUrl = imageUrl[position]
        holder.itemView.apply {
            glide.load(imgUrl).into(img_itemImageShopping)

            setOnClickListener {
                onItemClickListener?.let {
                    it(imgUrl)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return imageUrl.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}
