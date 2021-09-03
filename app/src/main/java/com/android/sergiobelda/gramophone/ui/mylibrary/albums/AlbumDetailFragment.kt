/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.AlbumDetailFragmentBinding
import com.android.sergiobelda.gramophone.model.Track
import com.android.sergiobelda.gramophone.viewmodel.MainViewModel
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumDetailViewModel

class AlbumDetailFragment : Fragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()

    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding: AlbumDetailFragmentBinding get() = _binding!!

    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var tracksAdapter: TracksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
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
            navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setAlbumData(albumId: String) {
        albumDetailViewModel.getAlbum(albumId)
        albumDetailViewModel.getTracksByAlbumId(albumId).observe(viewLifecycleOwner) {
            binding.tracksRecyclerView.layoutManager = LinearLayoutManager(context)
            tracksAdapter = TracksAdapter(it)
            tracksAdapter.trackSelectedListener = object : TracksAdapter.TrackSelectedListener {
                override fun onTrackSelected(track: Track) {
                    mainViewModel.select(track)
                }
            }
            binding.tracksRecyclerView.adapter = tracksAdapter
        }
    }

    companion object {
        const val TAG = "AlbumDetailFragment"
    }
}
