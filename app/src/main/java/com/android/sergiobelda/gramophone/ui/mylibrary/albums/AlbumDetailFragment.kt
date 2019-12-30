package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.android.sergiobelda.gramophone.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.android.sergiobelda.gramophone.databinding.AlbumDetailFragmentBinding
import com.android.sergiobelda.gramophone.ui.mylibrary.artists.ArtistDetailFragmentArgs
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumDetailViewModel
import kotlinx.android.synthetic.main.album_detail_fragment.*
import kotlinx.android.synthetic.main.fragment_songs.*

class AlbumDetailFragment : Fragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()

    private lateinit var binding : AlbumDetailFragmentBinding

    private val albumDetailViewModel by lazy { ViewModelProvider(this).get(AlbumDetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.albumImageView.transitionName = args.uri
        setToolbar()
        setData()
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setData() {
        val albumId = args.id
        albumId?.let {
            albumDetailViewModel.getAlbum(it)
            albumDetailViewModel.getTracksByAlbumId(it).observe(viewLifecycleOwner, Observer {
                tracks_recycler_view.layoutManager = LinearLayoutManager(context)
                tracks_recycler_view.adapter = TracksAdapter(it)
                Log.d(TAG, it.toString())
            })
        }
    }

    companion object {
        const val TAG = "AlbumDetailFragment"
    }
}
