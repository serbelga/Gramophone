package com.example.sergiobelda.gramophone.ui.mainmenu.pages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.util.Album
import com.example.sergiobelda.gramophone.util.Song
import kotlinx.android.synthetic.main.fragment_songs.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AlbumsFragment : Fragment() {
    lateinit var albumSelectedListener : AlbumSelectedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        val album = Album("The Dark Side Of The Moon", 1973, "LP", 1)
        val album1 = Album("The Dark Side Of The Moon", 1973, "LP", 1)
        val albums = ArrayList<Album>()
        albums.add(album)
        albums.add(album1)
        val adapter = AlbumsAdapter(albums, object : AlbumsAdapter.OnItemClickListener{
            override fun onItemClick(album: Album) {
                albumSelectedListener.onAlbumSelected(album)
            }
        })
        recyclerView.adapter = adapter
    }

    interface AlbumSelectedListener {
        fun onAlbumSelected(album: Album)
    }
}
