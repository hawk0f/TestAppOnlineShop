package com.hh.testapponlineshop.views.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.hh.testapponlineshop.adapters.FavouriteTabsViewPager
import com.hh.testapponlineshop.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment()
{
    private var _binding: FragmentFavouriteBinding? = null
    private val binding: FragmentFavouriteBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        val adapter = FavouriteTabsViewPager(childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.viewPagerFavourites.adapter = adapter

        binding.tabLayoutFavourites.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab?)
            {
                binding.viewPagerFavourites.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?)
            {
            }

            override fun onTabReselected(tab: TabLayout.Tab?)
            {
            }

        })

        binding.viewPagerFavourites.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int)
            {
                super.onPageSelected(position)
                binding.tabLayoutFavourites.selectTab(binding.tabLayoutFavourites.getTabAt(position))
            }
        })

        return binding.root
    }
}