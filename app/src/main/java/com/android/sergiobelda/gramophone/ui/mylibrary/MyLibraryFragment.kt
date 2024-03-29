/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.MyLibraryFragmentBinding
import com.android.sergiobelda.gramophone.ui.ErrorFragment
import com.android.sergiobelda.gramophone.ui.MainActivity
import com.android.sergiobelda.gramophone.ui.mylibrary.albums.AlbumsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.artists.ArtistsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.playlists.PlaylistsFragment

class MyLibraryFragment : Fragment() {
    private var _binding: MyLibraryFragmentBinding? = null
    private val binding: MyLibraryFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyLibraryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                (activity as MainActivity).expandAppBarLayout(expanded = true, animate = true)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                (activity as MainActivity).expandAppBarLayout(expanded = true, animate = true)
            }

            override fun onPageSelected(position: Int) {
                (activity as MainActivity).expandAppBarLayout(expanded = true, animate = true)
            }
        })
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ArtistsFragment()
                1 -> AlbumsFragment()
                2 -> PlaylistsFragment()
                else -> ErrorFragment()
            }
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> resources.getString(R.string.artists)
                1 -> resources.getString(R.string.albums)
                2 -> resources.getString(R.string.playlists)
                else -> null
            }
        }
    }
}
