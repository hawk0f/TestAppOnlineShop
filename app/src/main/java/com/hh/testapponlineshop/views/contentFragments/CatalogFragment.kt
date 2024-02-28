package com.hh.testapponlineshop.views.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.hh.testapponlineshop.R
import com.hh.testapponlineshop.adapters.CatalogItemAdapter
import com.hh.testapponlineshop.databinding.FragmentCatalogBinding
import com.hh.testapponlineshop.viewModels.CatalogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogFragment : Fragment()
{
    private var _binding: FragmentCatalogBinding? = null
    private val binding: FragmentCatalogBinding
        get() = _binding!!

    private val viewModel by viewModel<CatalogViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)

        val adapter = CatalogItemAdapter({
            viewModel.onCardClick(it)
        }, {
            viewModel.onFavouriteClick(it)
        }, {
            binding.catalogRecycler.scrollToPosition(0)
        })
        binding.catalogRecycler.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            viewModel.setFavouriteStatus()
        }

        viewModel.itemClicked.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(CatalogFragmentDirections.actionCatalogFragmentToCatalogItemInfoFragment(it))
                viewModel.onItemNavigated()
            }
        }

        viewModel.itemsUi.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.filterAll()
            }
        }

        viewModel.filteredList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }

        binding.tagsGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            binding.sortItemsMenu.setText("")
            for (i in group.children)
            {
                (i as Chip).isCloseIconVisible = false
            }
            if (checkedIds.isNotEmpty())
            {
                ((group.children.toList().first { it.id == checkedIds[0] }) as Chip).isCloseIconVisible = true

                when (group.children.indexOf(group.children.toList().first { it.id == checkedIds[0] }))
                {
                    0 ->
                    {
                        viewModel.filterAll()
                    }

                    1 ->
                    {
                        viewModel.filterFace()
                    }

                    2 ->
                    {
                        viewModel.filterBody()
                    }

                    3 ->
                    {
                        viewModel.filterSuntan()
                    }

                    4 ->
                    {
                        viewModel.filterMask()
                    }
                }
            }
            else
            {
                viewModel.filterAll()
            }
        }

        var isDropDownShown = false

        binding.sortItemsMenu.setOnClickListener {
            if (isDropDownShown)
            {
                binding.sortItemsMenu.dismissDropDown()
            }
            else
            {
                isDropDownShown = true
                binding.sortItemsMenu.showDropDown()
            }
        }

        binding.sortItemsMenu.setOnDismissListener {
            isDropDownShown = false
        }

        binding.sortItemsMenu.setOnItemClickListener { _, _, _, id ->
            when (id)
            {
                0L ->
                {
                    viewModel.sortByPopularity()
                }

                1L ->
                {
                    viewModel.sortByPriceDescending()
                }

                2L ->
                {
                    viewModel.sortByPrice()
                }
            }
        }

        return binding.root
    }

    override fun onResume()
    {
        super.onResume()
        ArrayAdapter.createFromResource(requireContext(), R.array.sort_menu_items, R.layout.sort_item).also { adapter ->
            adapter.setDropDownViewResource(R.layout.sort_item)
            binding.sortItemsMenu.dropDownWidth = resources.displayMetrics.widthPixels / 2
            binding.sortItemsMenu.setAdapter(adapter)
            binding.sortItemsMenu.setText(adapter.getItem(0), false)
        }
    }
}