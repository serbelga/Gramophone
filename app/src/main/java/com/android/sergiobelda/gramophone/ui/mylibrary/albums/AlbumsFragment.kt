/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.albums

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
import com.android.sergiobelda.gramophone.databinding.AlbumsFragmentBinding
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragmentDirections
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumsViewModel

/**
 * AlbumsFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class AlbumsFragment : Fragment() {
    private var _binding : AlbumsFragmentBinding? = null
    private val binding: AlbumsFragmentBinding get() = _binding!!

    private val albumsViewModel: AlbumsViewModel by viewModels()

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumsFragmentBinding.inflate(inflater, container, false)
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
                val action = MyLibraryFragmentDirections.navToAlbumDetail(id = album.id, uri = album.coverUri)
                if (album.coverUri != null) {
                    val extras = FragmentNavigatorExtras(
                        imageView to album.coverUri
                    )
                    findNavController().navigate(action, extras)
                } else {
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun setData() {
        albumsViewModel.albums.observe(viewLifecycleOwner) {
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
        const val TAG = "AlbumsFragment"
    }
}
