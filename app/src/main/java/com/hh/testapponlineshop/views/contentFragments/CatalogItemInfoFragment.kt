package com.hh.testapponlineshop.views.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hh.testapponlineshop.customViews.LayoutedTextView.OnLayoutListener
import com.hh.testapponlineshop.R
import com.hh.testapponlineshop.adapters.ItemImagesViewPagerAdapter
import com.hh.testapponlineshop.adapters.ItemInfoAdapter
import com.hh.testapponlineshop.databinding.FragmentCatalogItemInfoBinding
import com.hh.testapponlineshop.viewModels.CatalogItemInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogItemInfoFragment : Fragment()
{
    private var _binding: FragmentCatalogItemInfoBinding? = null
    private val binding: FragmentCatalogItemInfoBinding
        get() = _binding!!

    private val viewModel by viewModel<CatalogItemInfoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentCatalogItemInfoBinding.inflate(inflater, container, false)

        val itemUi = CatalogItemInfoFragmentArgs.fromBundle(requireArguments()).item

        viewModel.setItemUI(itemUi)

        binding.recyclerInfo.adapter = ItemInfoAdapter(itemUi.info)

        binding.viewPager.adapter = ItemImagesViewPagerAdapter(itemUi.pictures)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
        binding.tabLayout.touchables.forEach { it.isEnabled = false }

        binding.tvHideOrShowDescription.setOnClickListener {
            if (binding.descriptionText.visibility == View.VISIBLE)
            {
                binding.descriptionText.visibility = View.GONE
                binding.brandCard.visibility = View.GONE
                binding.tvHideOrShowDescription.text = "Подробнее"
            }
            else
            {
                binding.descriptionText.visibility = View.VISIBLE
                binding.brandCard.visibility = View.VISIBLE
                binding.tvHideOrShowDescription.text = "Скрыть"
            }
        }

        binding.tvHideOrShowIngredients.setOnClickListener {
            binding.ingredientsText.visibility = View.VISIBLE
            if (binding.tvHideOrShowIngredients.text == "Подробнее")
            {
                binding.ingredientsText.maxLines = Int.MAX_VALUE
                binding.tvHideOrShowIngredients.text = "Скрыть"
            }
            else
            {
                binding.ingredientsText.maxLines = 2
                binding.tvHideOrShowIngredients.text = "Подробнее"
            }
        }

        viewModel.updateFavouriteIcon.observe(viewLifecycleOwner) {
            if (itemUi.isFavourite)
            {
                binding.buttonFavorite.setImageResource(R.drawable.favourite_uncheck_icon)
            }
            else
            {
                binding.buttonFavorite.setImageResource(R.drawable.favourite_check_icon)
            }
            itemUi.isFavourite = !itemUi.isFavourite
        }

        binding.vm = viewModel

        binding.ingredientsText.setOnLayoutListener(object : OnLayoutListener
        {
            override fun onLayouted(view: TextView?)
            {
                binding.ingredientsText.removeOnLayoutListener()
                val lineCount = view!!.lineCount
                if (lineCount <= 2)
                {
                    binding.tvHideOrShowIngredients.visibility = View.GONE
                }
                else
                {
                    binding.ingredientsText.visibility = View.GONE
                    binding.tvHideOrShowIngredients.visibility = View.VISIBLE
                }
            }
        })

        return binding.root
    }
}