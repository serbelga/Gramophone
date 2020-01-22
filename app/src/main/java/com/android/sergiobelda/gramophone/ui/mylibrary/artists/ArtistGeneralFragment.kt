/*
 * Copyright (c) Gramophone 2019-2020.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistGeneralFragmentBinding
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.ui.mylibrary.albums.AlbumsAdapter
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel

class ArtistGeneralFragment : Fragment() {
    private lateinit var binding: ArtistGeneralFragmentBinding

    private lateinit var artistDetailViewModel: ArtistDetailViewModel

    private lateinit var albumsAdapter: AlbumsAdapter

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
        binding = DataBindingUtil.inflate(inflater, R.layout.artist_general_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setData()
        setRecyclerView()
    }

    private fun setAdapter() {
        albumsAdapter = AlbumsAdapter(context!!, arrayListOf())
        albumsAdapter.albumSelectedListener = object : AlbumsAdapter.AlbumSelectedListener {
            override fun onAlbumSelected(album: Album, imageView: ImageView) {
                val action = ArtistDetailFragmentDirections.navToArtistAlbumDetail(uri = album.coverUri, id = album.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun setData() {
        artistDetailViewModel.albums.observe(viewLifecycleOwner, Observer {
            albumsAdapter.setData(it)
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumsAdapter
        }
    }

    companion object {
        private const val TAG = "ArtistGeneral"
    }
}