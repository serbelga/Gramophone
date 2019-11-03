package com.android.sergiobelda.gramophone.ui.album


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.android.sergiobelda.gramophone.R
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_songs.*


class AlbumDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        /*
        val adapter = TracksAdapter(songs, object : TracksAdapter.OnItemClickListener {
            override fun onItemClick(track: Track) {

            }
        })
        recyclerView.adapter = adapter

         */
    }
}
