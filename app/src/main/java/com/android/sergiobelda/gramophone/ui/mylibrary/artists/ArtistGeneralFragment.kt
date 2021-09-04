/*
 * Copyright (c) Gramophone 2019-2020.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.sergiobelda.gramophone.databinding.ArtistGeneralFragmentBinding
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.ui.mylibrary.albums.AlbumsAdapter
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistGeneralFragment : Fragment() {
    private var _binding: ArtistGeneralFragmentBinding? = null
    private val binding: ArtistGeneralFragmentBinding get() = _binding!!

    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArtistGeneralFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setData()
        setRecyclerView()
    }

    private fun setAdapter() {
        albumsAdapter = AlbumsAdapter(requireContext(), arrayListOf())
        albumsAdapter.albumSelectedListener = object : AlbumsAdapter.AlbumSelectedListener {
            override fun onAlbumSelected(album: Album, imageView: ImageView) {
                val action = ArtistDetailFragmentDirections.navToArtistAlbumDetail(uri = album.coverUri, id = album.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun setData() {
        artistDetailViewModel.albums.observe(viewLifecycleOwner) {
            albumsAdapter.setData(it)
        }
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
