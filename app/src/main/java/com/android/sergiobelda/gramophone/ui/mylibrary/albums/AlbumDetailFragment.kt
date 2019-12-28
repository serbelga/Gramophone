package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.android.sergiobelda.gramophone.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sergiobelda.gramophone.databinding.AlbumDetailFragmentBinding
import com.android.sergiobelda.gramophone.ui.mylibrary.artists.ArtistDetailFragmentArgs
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumDetailViewModel
import kotlinx.android.synthetic.main.fragment_songs.*

class AlbumDetailFragment : Fragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()

    private lateinit var binding : AlbumDetailFragmentBinding

    private val albumDetailViewModel by lazy { ViewModelProvider(this).get(AlbumDetailViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setHasOptionsMenu(true)
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
        recyclerView.layoutManager = LinearLayoutManager(context)
        val albumId = args.id
        albumId?.let { albumDetailViewModel.getAlbum(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}
