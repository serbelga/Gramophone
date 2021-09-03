/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.sergiobelda.gramophone.databinding.ArtistInformationFragmentBinding
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel

/**
 * ArtistInformationFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistInformationFragment : Fragment() {
    private var _binding: ArtistInformationFragmentBinding? = null
    private val binding: ArtistInformationFragmentBinding get() = _binding!!

    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistInformationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val TAG = "ArtistInformation"
    }
}
