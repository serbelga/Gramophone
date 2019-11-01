package com.android.sergiobelda.gramophone.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.mediaplayer.MediaPlayerHolder
import com.android.sergiobelda.gramophone.ui.preferences.SettingsActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_player.*

class MainActivity : AppCompatActivity() {
    private lateinit var playerBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playerAdapter: MediaPlayerHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setBottomSheetBehavior()
        //mediaPlayer = MediaPlayer.create(this, R.raw.audio)
        playerAdapter = MediaPlayerHolder(this)
        /*
        playButton.setOnClickListener{
            /*
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()
            else mediaPlayer.start()
            */
        }*/

        // Set Toolbar as ActionBar

        setSupportActionBar(toolbar)
        supportActionBar!!.elevation = 0F
        toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);

        val navController = findNavController(R.id.navHostFragment)

        // Set ActionBar
        // AppBarConfiguration(navController.graph) -> Only considers the home fragment in the navigation graph as a top level
        // If you have a bottomNavigationView with multiple fragments to switch -> AppBarConfiguration(setOf(R.id.artistsFragment, R.id.albumsFragment))
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.myLibraryFragment, R.id.myProfileFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set BottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

        /*
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.albumsFragment) {
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }*/
    }

    /*
    private fun setBottomSheetBehavior() {
        playerBottomSheetBehavior = BottomSheetBehavior.from(playerBottomSheet)
        playerBottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback())
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

     */

    fun expandBottomSheet(){
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        playerBottomSheetBehavior.isHideable = false
    }

    private fun bottomSheetCallback() : BottomSheetBehavior.BottomSheetCallback {
        return object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    //BottomSheetBehavior.STATE_EXPANDED -> Log.d("state: ", "expanded")
                    //BottomSheetBehavior.STATE_COLLAPSED -> Log.d("state: ", "collapsed")
                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
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


}
