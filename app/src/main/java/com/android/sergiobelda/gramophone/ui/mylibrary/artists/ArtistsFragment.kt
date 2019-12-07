package com.android.sergiobelda.gramophone.ui.mylibrary.artists


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.ArtistsViewModel
import kotlinx.android.synthetic.main.fragment_artists.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ArtistsFragment : Fragment() {
    lateinit var artistsAdapter: ArtistsAdapter

    private var artists : ArrayList<Artist> = arrayListOf()

    private val viewModel by lazy { ViewModelProvider(this).get(ArtistsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistsAdapter = ArtistsAdapter(context!!, artists)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getData()
    }

    private fun setRecyclerView() {
        recycler_view.layoutManager = GridLayoutManager(context, 2)
        recycler_view.adapter = artistsAdapter
    }

    private fun getData() {
        viewModel.getArtists().observe(viewLifecycleOwner, Observer {
            artistsAdapter.setData(it)
        })
    }
}
