package com.example.sergiobelda.gramophone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.ui.preferences.SettingsActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var playerBottomSheetBehavior : BottomSheetBehavior<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
        setBottomSheetBehavior()
    }

    private fun setBottomSheetBehavior() {
        playerBottomSheetBehavior = BottomSheetBehavior.from(playerBottomSheet)
        playerBottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback())
        playerBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

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
                    BottomSheetBehavior.STATE_EXPANDED -> Log.d("state: ", "expanded")
                    BottomSheetBehavior.STATE_COLLAPSED -> Log.d("state: ", "collapsed")

                }
            }
        }
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


}
