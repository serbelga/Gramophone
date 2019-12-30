package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewpager.widget.ViewPager
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.databinding.ArtistDetailFragmentBinding
import com.android.sergiobelda.gramophone.ui.ErrorFragment
import com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists.ArtistDetailViewModel
import com.google.android.material.tabs.TabLayout

/**
 * ArtistDetailFragment
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistDetailFragment : Fragment() {
    private val args: ArtistDetailFragmentArgs by navArgs()

    private lateinit var binding: ArtistDetailFragmentBinding

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var artistDetailViewModel: ArtistDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        artistDetailViewModel = activity?.run {
            ViewModelProvider(this).get(ArtistDetailViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artist_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name = args.name
        binding.imageUri = args.uri
        binding.artistImageView.transitionName = args.uri

        initViews()

        setViewPager()
        setToolbar()

        artistDetailViewModel.getArtistInfo(args.name!!)
    }



    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initViews() {
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
    }

    private fun setViewPager() {
        val pagerAdapter = PagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(context!!, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ErrorFragment()
                1 -> ErrorFragment()
                2 -> ArtistInformationFragment()
                3 -> ErrorFragment()
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
