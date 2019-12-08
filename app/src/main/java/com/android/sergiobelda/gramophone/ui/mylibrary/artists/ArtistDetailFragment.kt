package com.android.sergiobelda.gramophone.ui.mylibrary.artists


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistDetailFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.artist_detail_fragment.*

/**
 * ArtistDetailFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistDetailFragment : Fragment() {

    private var artistImageUri: String? = null

    val args: ArtistDetailFragmentArgs by navArgs()

    lateinit var binding : ArtistDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.artist_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArtistImage()
        binding.name = args.name
    }

    private fun setArtistImage() {
        artistImageUri = args.uri
        artist_image_view.also {
            it.transitionName = artistImageUri
            Glide.with(this)
                .load(artistImageUri)
                .apply(com.bumptech.glide.request.RequestOptions.circleCropTransform())
                .into(it)
        }
    }
}
