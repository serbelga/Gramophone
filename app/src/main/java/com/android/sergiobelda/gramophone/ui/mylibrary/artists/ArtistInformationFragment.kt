package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.sergiobelda.gramophone.R

class ArtistInformationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist_information, container, false)
    }

    companion object {
        const val TAG = "ArtistInformation"
    }
}
