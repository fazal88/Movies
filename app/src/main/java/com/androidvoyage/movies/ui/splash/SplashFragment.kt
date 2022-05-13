package com.androidvoyage.movies.ui.splash


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.androidvoyage.movies.RootActivity
import com.androidvoyage.movies.databinding.ListFragmentBinding
import com.androidvoyage.movies.databinding.SplashFragmentBinding
import com.androidvoyage.movies.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Fazal Shaikh on 13/5/22
 */

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main){
            delay(3000)
            (requireActivity() as RootActivity).getNavController()
                .navigate(SplashFragmentDirections.actionSplashFragmentToListFragment())
        }
    }


}