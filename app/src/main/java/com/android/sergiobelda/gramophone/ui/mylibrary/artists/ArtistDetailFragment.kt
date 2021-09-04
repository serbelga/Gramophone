/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistDetailFragmentBinding
import com.android.sergiobelda.gramophone.ui.ErrorFragment
import com.android.sergiobelda.gramophone.viewmodel.MainViewModel
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * ArtistDetailFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {
    private lateinit var artistId: String

    private val args: ArtistDetailFragmentArgs by navArgs()

    private var _binding: ArtistDetailFragmentBinding? = null
    private val binding: ArtistDetailFragmentBinding get() = _binding!!

    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()

        setToolbar()
        setViewPager()

        setArtistInfo()
    }

    private fun setArtistInfo() {
        args.id?.let {
            artistId = it
            artistDetailViewModel.getAlbums(artistId)
        }
        mainViewModel.getArtistInfo(args.name!!)
    }

    private fun bindViews() {
        // binding.name = args.name
        // binding.imageUri = args.uri
        binding.artistImageView.transitionName = args.uri
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ArtistGeneralFragment()
                1 -> ArtistInformationFragment()
                // 2 -> ErrorFragment()
                // 3 -> ErrorFragment()
                else -> ErrorFragment()
            }
        }

        override fun getCount(): Int = 4

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> getString(R.string.general)
                1 -> getString(R.string.information)
                // 2 -> getString(R.string.related)
                // 3 -> getString(R.string.concerts)
                else -> null
            }
        }
    }

    companion object {
        const val TAG = "ArtistDetail"
    }
}
