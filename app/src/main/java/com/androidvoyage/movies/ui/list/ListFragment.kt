package com.androidvoyage.movies.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.movies.RootActivity
import com.androidvoyage.movies.data.model.MovieItem
import com.androidvoyage.movies.databinding.ListFragmentBinding
import com.androidvoyage.movies.setPageEndListener


/**
 * Created by Fazal Shaikh on 13/5/22
 */

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    //https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg
    //https://www.themoviedb.org/t/p/w1280/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg

    private val viewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initialiseViews()
        setObservers()
        return binding.root
    }

    private fun initialiseViews() {
        binding.swipeMovies.setOnRefreshListener {
            viewModel.listMovies.postValue(ArrayList())
            viewModel.getMovieList(1)
        }
        setPageEndListener(binding.rcvMovies) {
            viewModel.getMovieList()
        }
    }

    private fun setObservers() {
        viewModel.listMovies.observe(viewLifecycleOwner) {
            it?.let {
                if (viewModel.isLoading.value!!) {
                    if (it.isNotEmpty()) {
                        val oldList = ArrayList<MovieItem>()
                        oldList.addAll((binding.rcvMovies.adapter as MovieListAdapter).currentList)
                        oldList.addAll(it)
                        (binding.rcvMovies.adapter as MovieListAdapter).submitList(oldList)
                    }else{
                        (binding.rcvMovies.adapter as MovieListAdapter).submitList(it)
                    }
                }
            }
        }
        viewModel.clickedMovie.observe(viewLifecycleOwner) {
            it?.let {
                (requireActivity() as RootActivity).getNavController()
                    .navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.resetClickedMovie()
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            it?.let {
                binding.swipeMovies.isRefreshing = it
            }
        }
    }

}