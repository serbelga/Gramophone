/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistInformationFragmentBinding
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel

/**
 * ArtistInformationFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistInformationFragment : Fragment() {
    private lateinit var binding: ArtistInformationFragmentBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.artist_information_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = artistDetailViewModel
        return binding.root
    }

    companion object {
        private const val TAG = "ArtistInformation"
    }
}
