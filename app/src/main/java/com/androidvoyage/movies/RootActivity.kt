package com.androidvoyage.movies

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidvoyage.movies.ui.detail.DetailViewModel
import com.androidvoyage.movies.ui.list.ListViewModel
import com.androidvoyage.movies.utils.Status


/**
 * Created by Fazal Shaikh on 13/5/22
 */

class RootActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }


}