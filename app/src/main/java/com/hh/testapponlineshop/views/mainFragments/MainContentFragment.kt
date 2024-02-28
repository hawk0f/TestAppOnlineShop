package com.hh.testapponlineshop.views.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.hh.testapponlineshop.R
import com.hh.testapponlineshop.databinding.FragmentMainContentBinding

class MainContentFragment : Fragment()
{
    private var _binding: FragmentMainContentBinding? = null
    private val binding: FragmentMainContentBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentMainContentBinding.inflate(inflater, container, false)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_container_content) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        val builder = AppBarConfiguration.Builder(R.id.mainFragment, R.id.catalogFragment, R.id.basketFragment, R.id.saleFragment, R.id.profileFragment)
        val appBarConfiguration = builder.build()

        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)

        return binding.root
    }
}