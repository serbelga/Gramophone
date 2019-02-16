package com.example.sergiobelda.gramophone.ui.mainmenu.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.ui.mainmenu.tabs.*
import com.example.sergiobelda.gramophone.ui.ErrorFragment

class PagerAdapter(fm : FragmentManager, private var context : Context) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ArtistsFragment()
            1 -> AlbumsFragment()
            2 -> SongsFragment()
            3 -> CategoriesFragment()
            4 -> PlaylistsFragment()
            else -> ErrorFragment()
        }
    }

    override fun getCount(): Int  = 5

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> this.context.resources.getString(R.string.artists)
            1 -> this.context.resources.getString(R.string.albums)
            2 -> this.context.resources.getString(R.string.songs)
            3 -> this.context.resources.getString(R.string.categories)
            4 -> this.context.resources.getString(R.string.playlists)
            else -> null
        }
    }
}