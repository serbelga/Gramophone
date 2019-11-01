package com.android.sergiobelda.gramophone.ui.mylibrary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.entities.Album
import com.android.sergiobelda.gramophone.entities.Song
import com.android.sergiobelda.gramophone.ui.ErrorFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.pages.AlbumsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.pages.ArtistsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.pages.SongsFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.pages.PlaylistsFragment
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 * Use the [MyLibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyLibraryFragment : Fragment() {
    private var viewPager : ViewPager? = null
    private var tabLayout : TabLayout? = null

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
        val pagerAdapter = PagerAdapter(this.fragmentManager!!)
        viewPager?.adapter = pagerAdapter
        tabLayout?.setupWithViewPager(viewPager)
    }

    inner class PagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val fragment: Fragment?
            when (position) {
                0 -> {
                    fragment = ArtistsFragment()
                }
                1 -> {
                    fragment = AlbumsFragment()
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