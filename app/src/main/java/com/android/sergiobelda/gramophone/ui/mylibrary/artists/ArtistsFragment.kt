package com.android.sergiobelda.gramophone.ui.mylibrary.artists


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragmentDirections
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistsViewModel
import kotlinx.android.synthetic.main.fragment_artists.*

/**
 * ArtistsFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistsFragment : Fragment() {
    lateinit var artistsAdapter: ArtistsAdapter

    private var artists : ArrayList<Artist> = arrayListOf()

    private val viewModel by lazy { ViewModelProvider(this).get(ArtistsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistsAdapter = ArtistsAdapter(context!!, artists)
        artistsAdapter.artistSelectedListener = object : ArtistsAdapter.ArtistSelectedListener {
            override fun onArtistSelected(artist: Artist, imageView: ImageView) {
                val extras = FragmentNavigatorExtras(
                    imageView to artist.imageUri
                )
                val action = MyLibraryFragmentDirections.navToArtistDetail(uri = artist.imageUri, name = artist.name)
                findNavController().navigate(action, extras)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = artistsAdapter
        }
    }

    private fun getData() {
        viewModel.getArtists().observe(viewLifecycleOwner, Observer {
            artistsAdapter.setData(it)
        })
    }

    companion object {
        const val TAG = "ArtistFragment"
    }
}
