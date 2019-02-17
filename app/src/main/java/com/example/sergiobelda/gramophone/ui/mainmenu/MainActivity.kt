package com.example.sergiobelda.gramophone.ui.mainmenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.ui.ErrorFragment
import com.example.sergiobelda.gramophone.ui.mainmenu.pages.*
import com.example.sergiobelda.gramophone.ui.preferences.SettingsActivity
import com.example.sergiobelda.gramophone.util.Song
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var playerBottomSheetBehavior : BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
        setViewPager()
        setBottomSheetBehavior()
    }

    private fun setBottomSheetBehavior() {
        playerBottomSheetBehavior = BottomSheetBehavior.from(playerBottomSheet)
        playerBottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback())
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun bottomSheetCallback() : BottomSheetBehavior.BottomSheetCallback {
        return object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> Log.d("state: ", "expanded")
                    BottomSheetBehavior.STATE_COLLAPSED -> Log.d("state: ", "collapsed")

                }
            }
        }
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
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
                }
                2 -> {
                    fragment = SongsFragment()
                    fragment.songSelectedListener = object : SongsFragment.SongSelectedListener {
                        override fun onSongSelected(song: Song) {
                            playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                            playerSongTitleTextView.text = song.title
                            playerSongArtistTextView.text = song.artist
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
