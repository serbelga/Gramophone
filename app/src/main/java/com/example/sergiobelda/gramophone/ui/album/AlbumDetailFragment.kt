package com.example.sergiobelda.gramophone.ui.album


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.sergiobelda.gramophone.R
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sergiobelda.gramophone.ui.mainmenu.pages.SongsAdapter
import com.example.sergiobelda.gramophone.entities.Song
import kotlinx.android.synthetic.main.fragment_songs.*


class AlbumDetailFragment : Fragment() {
    var behavior: CoordinatorLayout.Behavior<*>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*
        if (behavior != null)
            return

        val layout = activity!!.findViewById(R.id.frameLayout) as FrameLayout
        val params = layout.layoutParams as CoordinatorLayout.LayoutParams

        behavior = params.behavior
        params.behavior = null
        */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val song = Song("Money", "Pink Floyd")
        val songs = ArrayList<Song>()
        songs.add(song)
        val adapter = SongsAdapter(songs, object : SongsAdapter.OnItemClickListener{
            override fun onItemClick(song: Song) {

            }
        })
        recyclerView.adapter = adapter



    }
}
