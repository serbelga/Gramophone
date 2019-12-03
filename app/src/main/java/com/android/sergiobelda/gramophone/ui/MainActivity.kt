package com.android.sergiobelda.gramophone.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintsChangedListener
import androidx.core.animation.doOnStart
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import coil.api.load
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.data.theDarkSide
import com.android.sergiobelda.gramophone.data.theWall
import com.android.sergiobelda.gramophone.mediaplayer.MediaPlayerHolder
import com.android.sergiobelda.gramophone.ui.preferences.SettingsActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var playerBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var playerAdapter: MediaPlayerHolder

    private lateinit var coverSmallImageView: ImageView
    private lateinit var coverLargeImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
        toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
        //motion_layout.setTransitionListener(this)

        setViews()
        setBottomSheetBehavior()
        setBottomSheetData()
        setNavigation()
    }

    private fun setViews() {
        coverSmallImageView = findViewById(R.id.cover_small_image_view)
        coverLargeImageView = findViewById(R.id.cover_large_image_view)

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
    private fun setBottomSheetData() {
        Glide.with(this)
            .load(theDarkSide.coverUri)
            .transform(RoundedCorners(16))
            .into(coverLargeImageView)
        Glide.with(this)
            .load(theDarkSide.coverUri)
            .transform(RoundedCorners(16))
            .into(coverSmallImageView)
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
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            /*
            if (destination.id == R.id.albumsFragment) {
                bottomNavigationView.visibility = View.VISIBLE
            } else {
                bottomNavigationView.visibility = View.GONE
            }*/
            expandAppBarLayout(expanded = true, animate = true)
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
                animateBottomNavigationView(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    //BottomSheetBehavior.STATE_COLLAPSED -> Log.d("state: ", "collapsed")

                }
            }
        }
    }


    private fun animateBottomNavigationView(@FloatRange(from = 0.0, to = 1.0) translationY: Float) {
        bottomNavigationView.translationY = translationY * bottomNavigationView.height
    }

    private fun animateContentScrim(@FloatRange(from = 0.0, to = 1.0) alpha: Float) {
        content_scrim.alpha = alpha
    }

    fun expandBottomSheet() {
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    /**
     *
     */
    fun expandAppBarLayout(expanded: Boolean, animate: Boolean){
        appbar.setExpanded(expanded, animate)
    }
}