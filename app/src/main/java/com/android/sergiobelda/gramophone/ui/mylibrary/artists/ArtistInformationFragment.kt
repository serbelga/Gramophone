/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.sergiobelda.gramophone.databinding.ArtistInformationFragmentBinding
import com.android.sergiobelda.gramophone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * ArtistInformationFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
@AndroidEntryPoint
class ArtistInformationFragment : Fragment() {
    private var _binding: ArtistInformationFragmentBinding? = null
    private val binding: ArtistInformationFragmentBinding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistInformationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.bio.observe(viewLifecycleOwner) {
            binding.bio.text = it?.content
        }
    }

    companion object {
        private const val TAG = "ArtistInformation"
    }
}
