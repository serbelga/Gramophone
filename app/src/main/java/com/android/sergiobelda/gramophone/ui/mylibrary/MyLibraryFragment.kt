package com.android.sergiobelda.gramophone.ui.mylibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.ui.ErrorFragment
import com.android.sergiobelda.gramophone.ui.MainActivity
import com.android.sergiobelda.gramophone.ui.mylibrary.albums.AlbumsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.artists.ArtistsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.playlists.PlaylistsFragment
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 * Use the [MyLibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyLibraryFragment : Fragment() {
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
        setViewPager()
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(childFragmentManager)
        viewPager?.adapter = pagerAdapter
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        tabLayout?.setupWithViewPager(viewPager)
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            val fragment: Fragment?
            when (position) {
                0 -> {
                    fragment =
                        ArtistsFragment()
                }
                1 -> {
                    fragment =
                        AlbumsFragment()
                    fragment.albumSelectedListener = object : AlbumsFragment.AlbumSelectedListener {
                        override fun onAlbumSelected(album: Album) {

                        }
                    }
                }
                2 -> {
                    fragment =
                        PlaylistsFragment()
                }
                else -> {
                    fragment = ErrorFragment()
                }
            }
            return fragment
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
