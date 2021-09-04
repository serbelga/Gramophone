/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.sergiobelda.gramophone.databinding.ArtistsFragmentBinding
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragmentDirections
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * ArtistsFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
@AndroidEntryPoint
class ArtistsFragment : Fragment() {
    private var _binding: ArtistsFragmentBinding? = null
    private val binding: ArtistsFragmentBinding get() = _binding!!

    private val artistsViewModel: ArtistsViewModel by viewModels()

    private lateinit var artistsAdapter: ArtistsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setData()
        setRecyclerView()
    }

    private fun setAdapter() {
        artistsAdapter = ArtistsAdapter(requireContext(), arrayListOf())
        artistsAdapter.artistSelectedListener = object : ArtistsAdapter.ArtistSelectedListener {
            override fun onArtistSelected(artist: Artist, imageView: ImageView) {
                val action = MyLibraryFragmentDirections.navToArtistDetail(id = artist.id, uri = artist.imageUri, name = artist.name)
                if (artist.imageUri != null) {
                    val extras = FragmentNavigatorExtras(
                        imageView to artist.imageUri
                    )
                    findNavController().navigate(action, extras)
                } else {
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = artistsAdapter
        }
    }

    private fun setData() {
        artistsViewModel.artists.observe(viewLifecycleOwner) {
            artistsAdapter.setData(it)
        }
    }

    companion object {
        const val TAG = "ArtistFragment"
    }
}
