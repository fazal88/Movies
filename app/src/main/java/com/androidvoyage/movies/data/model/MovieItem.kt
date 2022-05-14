package com.androidvoyage.movies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Fazal Shaikh on 13/5/22
 */

@Parcelize
data class MovieItem(
    val adult: Boolean,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Float,
    val vote_count: String
) : Parcelable