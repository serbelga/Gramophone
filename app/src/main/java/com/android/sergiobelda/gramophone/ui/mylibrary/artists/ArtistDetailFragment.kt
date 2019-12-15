package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistDetailFragmentBinding
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.ui.ErrorFragment
import com.android.sergiobelda.gramophone.ui.mylibrary.MyLibraryFragment
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.artist_detail_fragment.*

/**
 * ArtistDetailFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistDetailFragment : Fragment() {
    private val args: ArtistDetailFragmentArgs by navArgs()

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
        binding.name = args.name
        binding.imageUri = args.uri
        binding.artistImageView.transitionName = args.uri

        val pagerAdapter = PagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    inner class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ArtistInformationFragment()
                1 -> ArtistInformationFragment()
                2 -> ArtistInformationFragment()
                3 -> ArtistInformationFragment()
                else -> ErrorFragment()
            }
        }

        override fun getCount(): Int = 4

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> getString(R.string.general)
                1 -> getString(R.string.related)
                2 -> getString(R.string.information)
                3 -> getString(R.string.concerts)
                else -> null
            }
        }
    }

    companion object {
        const val TAG = "ArtistDetail"
    }
}
