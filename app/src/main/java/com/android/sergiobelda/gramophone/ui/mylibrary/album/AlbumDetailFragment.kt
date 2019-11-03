package com.android.sergiobelda.gramophone.ui.mylibrary.album


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.android.sergiobelda.gramophone.R
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sergiobelda.gramophone.ui.mylibrary.pages.TracksAdapter
import com.android.sergiobelda.gramophone.model.Track
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
