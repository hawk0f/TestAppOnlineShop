package com.hh.testapponlineshop.views.contentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hh.testapponlineshop.databinding.FragmentProfileBinding
import com.hh.testapponlineshop.viewModels.ProfileViewModel
import com.hh.testapponlineshop.views.mainFragments.MainContentFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment()
{
    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding
        get() = _binding!!

    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel.getAmountOfFavourites()

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userHasDeleted.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(context, "Аккаунт был удалён!", Toast.LENGTH_LONG).show()
                viewModel.onLogoutClicked()
                //MainContentFragment
                requireParentFragment().requireParentFragment().findNavController().navigate(MainContentFragmentDirections.actionMainContentFragmentToRegistrationFragment())
            }
        }

        binding.favouritesCard.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToFavouriteFragment())
        }

        return binding.root
    }
}