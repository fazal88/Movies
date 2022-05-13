package com.androidvoyage.movies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Fazal Shaikh on 13/5/22
 */


@Parcelize
data class MovieListResponse(
    val page: Int,
    val total_pages: Long,
    val total_results: Long,
    val results: List<MovieItem>
): Parcelable