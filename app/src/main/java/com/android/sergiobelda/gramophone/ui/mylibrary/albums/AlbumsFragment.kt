/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.albums

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
import com.android.sergiobelda.gramophone.databinding.AlbumsFragmentBinding
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragmentDirections
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumsViewModel

/**
 * AlbumsFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class AlbumsFragment : Fragment() {
    private lateinit var binding : AlbumsFragmentBinding

    private val albumsViewModel by lazy { ViewModelProvider(this).get(AlbumsViewModel::class.java) }

    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.albums_fragment, container, false)
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
        albumsViewModel.albums.observe(viewLifecycleOwner, Observer {
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
        const val TAG = "AlbumsFragment"
    }
}
