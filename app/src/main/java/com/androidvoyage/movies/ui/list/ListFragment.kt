package com.androidvoyage.movies.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.movies.RootActivity
import com.androidvoyage.movies.databinding.ListFragmentBinding
import com.androidvoyage.movies.ui.detail.DetailViewModel
import com.androidvoyage.movies.utils.Status


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
        setObservers()
        return binding.root
    }

    private fun setObservers() {

        viewModel.listMovies.observe(viewLifecycleOwner){
            it?.let {
                (binding.rcvMovies.adapter as MovieListAdapter).submitList(it)
            }
        }

        viewModel.clickedMovie.observe(viewLifecycleOwner){
            it?.let {
                (requireActivity() as RootActivity).getNavController()
                    .navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.resetClickedMovie()
            }
        }




        /*viewModel.getMovieList().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { response ->
                            Log.d("RootActivity -> List", "SUCCESS: $response")

                        }
                    }
                    Status.ERROR -> {
                        Log.d("RootActivity -> List", "ERROR: ")
                        viewModel.errorMessage.postValue(resource.message)
                    }
                    Status.LOADING -> {
                        Log.d("RootActivity -> List", "LOADING:")
                        viewModel.errorMessage.postValue("Loading...")
                    }
                }
            }
        })*/
    }

}