package com.example.sergiobelda.gramophone.ui.mainmenu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.ui.ErrorFragment
import com.example.sergiobelda.gramophone.ui.mainmenu.pages.*
import com.example.sergiobelda.gramophone.entities.Album
import com.example.sergiobelda.gramophone.entities.Song
import kotlinx.android.synthetic.main.fragment_menu.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(this.fragmentManager!!)
        //viewPager.adapter = pagerAdapter
        //tabLayout.setupWithViewPager(viewPager)
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
                    fragment = SongsFragment()
                    fragment.songSelectedListener = object : SongsFragment.SongSelectedListener {
                        override fun onSongSelected(song: Song) {
                            //playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                            //playerSongTitleTextView.text = song.title
                            //playerSongArtistTextView.text = song.artist

                        }
                    }
                }
                3 -> {
                    fragment = CategoriesFragment()
                }
                4 -> {
                    fragment = PlaylistsFragment()
                }
                else -> {
                    fragment = ErrorFragment()
                }
            }
            return fragment
        }

        override fun getCount(): Int  = 5

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> resources.getString(R.string.artists)
                1 -> resources.getString(R.string.albums)
                2 -> resources.getString(R.string.songs)
                3 -> resources.getString(R.string.categories)
                4 -> resources.getString(R.string.playlists)
                else -> null
            }
        }
    }
}
