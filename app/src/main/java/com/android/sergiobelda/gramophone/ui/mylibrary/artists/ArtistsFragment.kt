/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistsFragmentBinding
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragmentDirections
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistsViewModel
import kotlinx.android.synthetic.main.artists_fragment.*

/**
 * ArtistsFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistsFragment : Fragment() {
    private lateinit var binding: ArtistsFragmentBinding

    private val artistsViewModel by lazy { ViewModelProvider(this).get(ArtistsViewModel::class.java) }

    private lateinit var artistsAdapter: ArtistsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artists_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getArtists()
        setRecyclerView()
    }

    private fun setAdapter() {
        artistsAdapter = ArtistsAdapter(context!!, arrayListOf())
        artistsAdapter.artistSelectedListener = object : ArtistsAdapter.ArtistSelectedListener {
            override fun onArtistSelected(artist: Artist, imageView: ImageView) {
                val action = MyLibraryFragmentDirections.navToArtistDetail(uri = artist.imageUri, name = artist.name)
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

    private fun getArtists() {
        artistsViewModel.artists.observe(viewLifecycleOwner, Observer {
            artistsAdapter.setData(it)
        })
    }

    companion object {
        const val TAG = "ArtistFragment"
    }
}
