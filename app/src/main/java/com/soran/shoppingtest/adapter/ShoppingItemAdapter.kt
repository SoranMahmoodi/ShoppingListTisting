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
import com.soran.shoppingtest.model.ShoppingItem
import com.soran.shoppingtest.utils.OnClickItemImagePick
import kotlinx.android.synthetic.main.item_image_pick.view.*
import kotlinx.android.synthetic.main.item_shopping.view.*
import javax.inject.Inject

class ShoppingItemAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var shoppingItem: List<ShoppingItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    /* private var onItemClickListener: ((String) -> Unit)? = null
     fun setOnClickItemRv(listener: (String) -> Unit) {
         onItemClickListener = listener
     }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val imgUrl = shoppingItem[position]
        holder.itemView.apply {
            tv_itemShopping_name.text = imgUrl.name
            tv_itemShopping_amount.text = imgUrl.amount.toString()
            tv_itemShopping_price.text = imgUrl.price.toString()

        }
    }

    override fun getItemCount(): Int {
        return shoppingItem.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}
