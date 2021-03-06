package com.androidvoyage.movies.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.androidvoyage.movies.databinding.DetailFragmentBinding
import com.androidvoyage.movies.databinding.ListFragmentBinding
import com.androidvoyage.movies.onClickWithAnimation
import com.androidvoyage.movies.setOnClickAnimateListener


/**
 * Created by Fazal Shaikh on 13/5/22
 */

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    //https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg
    //https://www.themoviedb.org/t/p/w1280/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }
    private lateinit var args : DetailFragmentArgs
    private lateinit var binding: DetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        args = arguments?.let { DetailFragmentArgs.fromBundle(it) }!!
        binding.vm= viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initialiseViews()
        return binding.root
    }

    private fun initialiseViews() {
        viewModel.getMovieDetail(args.movie.id)

        binding.ivBack.onClickWithAnimation { requireActivity().onBackPressed() }

        binding.swipeDetails.setOnRefreshListener {
            viewModel.getMovieDetail(args.movie.id)
            binding.swipeDetails.isRefreshing = false
        }
    }

}