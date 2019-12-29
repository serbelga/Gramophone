package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragmentDirections
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums.AlbumsViewModel
import kotlinx.android.synthetic.main.fragment_albums.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AlbumsFragment : Fragment() {
    private lateinit var albumsAdapter: AlbumsAdapter

    private val albumsViewModel by lazy { ViewModelProvider(this).get(AlbumsViewModel::class.java) }

    private var albums: ArrayList<Album> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsAdapter = AlbumsAdapter(context!!, albums)
        albumsAdapter.albumSelectedListener = object : AlbumsAdapter.AlbumSelectedListener {
            override fun onAlbumSelected(album: Album) {
                Log.d(TAG, album.id)
                Log.d(TAG, album.title)
                val action = MyLibraryFragmentDirections.navToAlbumDetail(id = album.id)
                findNavController().navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumsAdapter
        }
    }

    private fun getData() {
        albumsViewModel.albums.observe(viewLifecycleOwner, Observer {
            albumsAdapter.setData(it)
        })
    }

    companion object {
        const val TAG = "AlbumsFragment"
    }
}
