package com.hh.testapponlineshop.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hh.testapponlineshop.views.contentFragments.BrandsFragment
import com.hh.testapponlineshop.views.contentFragments.FavouriteListFragment

class FavouriteTabsViewPager(fragmentManager: FragmentManager, lifeCycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifeCycle)
{
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment
    {
        return if (position == 0)
        {
            FavouriteListFragment()
        }
        else
        {
            BrandsFragment()
        }
    }
}