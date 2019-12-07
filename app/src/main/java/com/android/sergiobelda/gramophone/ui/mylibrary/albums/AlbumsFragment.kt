package com.android.sergiobelda.gramophone.ui.mylibrary.albums


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Album
import kotlinx.android.synthetic.main.fragment_songs.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AlbumsFragment : Fragment() {
    lateinit var albumSelectedListener: AlbumSelectedListener

    private val viewModel by lazy { ViewModelProvider(this).get(AlbumsViewModel::class.java) }

    private var albums : ArrayList<Album> = arrayListOf()

    private var albumsAdapter = AlbumsAdapter(albums)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getData()
    }

    private fun setRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = albumsAdapter
    }

    private fun getData() {
        viewModel.getAlbums().observe(viewLifecycleOwner, Observer {
            albumsAdapter.setData(it)
        })
    }

    interface AlbumSelectedListener {
        fun onAlbumSelected(album: Album)
    }
}
