package com.hh.testapponlineshop.diffitemcallback

import androidx.recyclerview.widget.DiffUtil
import com.hh.testapponlineshop.models.ItemUI

class CatalogDiffItemCallback : DiffUtil.ItemCallback<ItemUI>()
{
    override fun areItemsTheSame(oldItem: ItemUI, newItem: ItemUI): Boolean = (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: ItemUI, newItem: ItemUI): Boolean = (oldItem == newItem)
}