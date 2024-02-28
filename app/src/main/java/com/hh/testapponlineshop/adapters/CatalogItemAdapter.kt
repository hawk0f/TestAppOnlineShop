package com.hh.testapponlineshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.hh.testapponlineshop.R
import com.hh.testapponlineshop.databinding.CatalogItemBinding
import com.hh.testapponlineshop.diffitemcallback.CatalogDiffItemCallback
import com.hh.testapponlineshop.models.ItemUI

class CatalogItemAdapter(private val cardClickListener: (item: ItemUI) -> Unit, private val favouriteClickListener: (item: ItemUI) -> Unit, private val scrollToTop: () -> Unit) : ListAdapter<ItemUI, CatalogItemAdapter.CatalogItemViewHolder>(CatalogDiffItemCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemViewHolder = CatalogItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CatalogItemViewHolder, position: Int)
    {
        val item = getItem(position)
        holder.bind(item, favouriteClickListener, cardClickListener)
    }

    override fun onCurrentListChanged(previousList: MutableList<ItemUI>, currentList: MutableList<ItemUI>)
    {
        super.onCurrentListChanged(previousList, currentList)
        scrollToTop()
    }

    class CatalogItemViewHolder(private val binding: CatalogItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: ItemUI, favouriteClickListener: (item: ItemUI) -> Unit, cardClickListener: (item: ItemUI) -> Unit)
        {
            binding.item = item
            binding.root.setOnClickListener {
                cardClickListener(item)
            }
            binding.viewPager.adapter = ItemImagesViewPagerAdapter(item.pictures)
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

            binding.tabLayout.touchables.forEach { it.isEnabled = false }

            binding.buttonFavorite.setOnClickListener {
                favouriteClickListener(item)
                if (item.isFavourite)
                {
                    binding.buttonFavorite.setImageResource(R.drawable.favourite_uncheck_icon)
                }
                else
                {
                    binding.buttonFavorite.setImageResource(R.drawable.favourite_check_icon)
                }
                item.isFavourite = !item.isFavourite
            }
        }

        companion object
        {
            fun inflateFrom(parent: ViewGroup): CatalogItemViewHolder
            {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CatalogItemBinding.inflate(layoutInflater, parent, false)
                return CatalogItemViewHolder(binding)
            }
        }
    }
}