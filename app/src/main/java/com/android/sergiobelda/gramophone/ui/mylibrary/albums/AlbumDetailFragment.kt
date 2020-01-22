/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.onNavDestinationSelected

import com.android.sergiobelda.gramophone.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.android.sergiobelda.gramophone.databinding.AlbumDetailFragmentBinding
import com.android.sergiobelda.gramophone.model.Track
import com.android.sergiobelda.gramophone.viewmodel.MainViewModel
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumDetailViewModel

class AlbumDetailFragment : Fragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()

    private lateinit var binding: AlbumDetailFragmentBinding

    private val albumDetailViewModel by lazy { ViewModelProvider(this).get(AlbumDetailViewModel::class.java) }

    private lateinit var mainViewModel: MainViewModel

    private lateinit var tracksAdapter: TracksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.album_detail_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = albumDetailViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        binding.albumImageView.transitionName = args.uri
        args.id?.let {
            setAlbumData(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.album_menu, menu)
    }

    // TODO must go on Activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setAlbumData(albumId: String) {
        albumDetailViewModel.getAlbum(albumId)
        albumDetailViewModel.getTracksByAlbumId(albumId).observe(viewLifecycleOwner, Observer {
            binding.tracksRecyclerView.layoutManager = LinearLayoutManager(context)
            tracksAdapter = TracksAdapter(it)
            tracksAdapter.trackSelectedListener = object : TracksAdapter.TrackSelectedListener {
                override fun onTrackSelected(track: Track) {
                    mainViewModel.select(track)
                }
            }
            binding.tracksRecyclerView.adapter = tracksAdapter
        })
    }

    companion object {
        const val TAG = "AlbumDetailFragment"
    }
}
