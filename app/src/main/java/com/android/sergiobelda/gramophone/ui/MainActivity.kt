package com.android.sergiobelda.gramophone.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.FloatRange
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.data.money
import com.android.sergiobelda.gramophone.databinding.MainActivityBinding
import com.android.sergiobelda.gramophone.mediaplayer.MediaPlayerHolder
import com.android.sergiobelda.gramophone.ui.preferences.SettingsActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 90
    }

    private lateinit var playerBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playerAdapter: MediaPlayerHolder

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        playerAdapter = MediaPlayerHolder(this)

        // Set Toolbar as ActionBar
        setSupportActionBar(main_toolbar)
        // motion_layout.setTransitionListener(this)
        bindTrack()
        setBottomSheetBehavior()
        setNavigation()

        checkPermissions()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
            }
        } else {
            // Permission has already been granted
            return
        }
    }

    override fun onResume() {
        super.onResume()
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted
                } else {
                    // permission denied.
                }
                return
            }
            else -> {}
        }

    }

    private fun bindTrack() {
        binding.track = money
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

    /**
     *
     */
    private fun setNavigation() {
        val navController = findNavController(R.id.navHostFragment)

        // Set ActionBar
        // AppBarConfiguration(navController.graph) -> Only considers the home fragment in the navigation graph as a top level
        // Declare which fragments are the top level destinations
        // If you have a bottomNavigationView with multiple fragments to switch -> AppBarConfiguration(setOf(R.id.fragment1, R.id.fragment2, ...))
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.myLibraryFragment,
                R.id.myProfileFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        // Set BottomNavigationView
        // bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.artistDetailFragment -> expandAppBarLayout(expanded = false, animate = false)
                R.id.albumDetailFragment -> expandAppBarLayout(expanded = false, animate = false)
                else -> {
                    expandAppBarLayout(expanded = true, animate = true)
                    supportActionBar?.let {
                        it.setDisplayHomeAsUpEnabled(true)
                        it.setHomeAsUpIndicator(R.drawable.ic_search_black_24dp)
                    }
                }
            }
        }
    }

    private fun setBottomSheetBehavior() {
        playerBottomSheetBehavior = BottomSheetBehavior.from(player_bottom_sheet)
        playerBottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback())
    }

    private fun bottomSheetCallback(): BottomSheetBehavior.BottomSheetCallback {
        return object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                player_bottom_sheet.setInterpolatedProgress(slideOffset)
                animateContentScrim(slideOffset)
                //animateBottomNavigationView(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    // BottomSheetBehavior.STATE_COLLAPSED -> Log.d("state: ", "collapsed")
                }
            }
        }
    }

    /*
    private fun animateBottomNavigationView(@FloatRange(from = 0.0, to = 1.0) translationY: Float) {
        bottomNavigationView.translationY = translationY * bottomNavigationView.height
    }
    */

    private fun animateContentScrim(@FloatRange(from = 0.0, to = 1.0) alpha: Float) {
        content_scrim.alpha = alpha
    }

    fun expandBottomSheet() {
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    /**
     *
     */
    fun expandAppBarLayout(expanded: Boolean, animate: Boolean) {
        appbar.setExpanded(expanded, animate)
    }
}