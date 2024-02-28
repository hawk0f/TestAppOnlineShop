package com.hh.testapponlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.hh.testapponlineshop.databinding.ViewPagerImageBinding

class ItemImagesViewPagerAdapter(private val pictures: List<Int>) : RecyclerView.Adapter<ItemImagesViewPagerAdapter.ImagesViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder = ImagesViewHolder.inflateFrom(parent)

    override fun getItemCount(): Int
    {
        return pictures.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int)
    {
        holder.bind(pictures[position])
    }

    class ImagesViewHolder(private val binding: ViewPagerImageBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(@DrawableRes image: Int)
        {
            binding.imageView.setImageResource(image)
        }

        companion object
        {
            fun inflateFrom(parent: ViewGroup): ImagesViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewPagerImageBinding.inflate(layoutInflater, parent, false)
                return ImagesViewHolder(binding)
            }
        }
    }
}