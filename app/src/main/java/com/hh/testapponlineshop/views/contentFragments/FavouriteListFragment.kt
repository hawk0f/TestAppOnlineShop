package com.hh.testapponlineshop.views.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hh.testapponlineshop.adapters.CatalogItemAdapter
import com.hh.testapponlineshop.databinding.FragmentFavouriteListBinding
import com.hh.testapponlineshop.viewModels.FavouriteListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteListFragment : Fragment()
{
    private var _binding: FragmentFavouriteListBinding? = null
    private val binding: FragmentFavouriteListBinding
        get() = _binding!!

    private val viewModel by viewModel<FavouriteListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)

        viewModel.loadFavouriteItems()

        val adapter = CatalogItemAdapter({
            viewModel.onCardClick(it)
        }, {
            viewModel.onFavouriteClick(it)
        }, {
            binding.favouriteRecycler.scrollToPosition(0)
        })
        binding.favouriteRecycler.adapter = adapter


        viewModel.itemClicked.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToCatalogItemInfoFragment(it))
                viewModel.onItemNavigated()
            }
        }

        viewModel.itemsUi.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
                if (it.isEmpty())
                {
                    binding.favouriteRecycler.visibility = View.GONE
                    binding.tvNoFavourites.visibility = View.VISIBLE
                }
                else
                {
                    binding.favouriteRecycler.visibility = View.VISIBLE
                    binding.tvNoFavourites.visibility = View.GONE
                }
            }
        }

        return binding.root
    }
}