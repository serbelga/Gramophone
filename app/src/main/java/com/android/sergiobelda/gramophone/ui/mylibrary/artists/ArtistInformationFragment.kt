package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel
import kotlinx.android.synthetic.main.fragment_artist_information.*

class ArtistInformationFragment : Fragment() {
    private lateinit var artistDetailViewModel: ArtistDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistDetailViewModel = activity?.run {
            ViewModelProvider(this).get(ArtistDetailViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artistDetailViewModel.bio.observe(viewLifecycleOwner, Observer {
            bio.text = it.content
        })
    }

    companion object {
        const val TAG = "ArtistInformation"
    }
}
