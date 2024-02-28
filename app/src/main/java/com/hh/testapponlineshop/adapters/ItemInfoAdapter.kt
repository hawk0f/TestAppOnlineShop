package com.hh.testapponlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hh.testapponlineshop.databinding.InfoItemBinding
import com.hh.testapponlineshop.domain.models.Info

class ItemInfoAdapter(private val infoList: List<Info>) : RecyclerView.Adapter<ItemInfoAdapter.ItemInfoViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemInfoViewHolder = ItemInfoViewHolder.inflateFrom(parent)

    override fun getItemCount(): Int = infoList.size

    override fun onBindViewHolder(holder: ItemInfoViewHolder, position: Int)
    {
        val item = infoList[position]
        holder.bind(item)
    }

    class ItemInfoViewHolder(private val binding: InfoItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(info: Info)
        {
            binding.info = info
        }

        companion object
        {
            fun inflateFrom(parent: ViewGroup): ItemInfoViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = InfoItemBinding.inflate(layoutInflater, parent, false)
                return ItemInfoViewHolder(binding)
            }
        }
    }
}
