package com.example.sergiobelda.gramophone.ui.mainmenu.pages


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.util.Song
import kotlinx.android.synthetic.main.fragment_songs.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SongsFragment : Fragment() {
    lateinit var songSelectedListener : SongSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val song = Song("Money", "Pink Floyd")
        val song1 = Song("Stairway to Heaven", "Led Zeppelin")
        val songs = ArrayList<Song>()
        songs.add(song)
        songs.add(song1)
        val adapter = SongsAdapter(songs, object : SongsAdapter.OnItemClickListener{
            override fun onItemClick(song: Song) {
                songSelectedListener.onSongSelected(song)
            }
        })
        recyclerView.adapter = adapter
    }

    interface SongSelectedListener {
        fun onSongSelected(song: Song)
    }
}
