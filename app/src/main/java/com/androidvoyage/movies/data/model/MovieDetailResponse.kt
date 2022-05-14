package com.androidvoyage.movies.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.math.roundToInt


/**
 * Created by Fazal Shaikh on 13/5/22
 */


@Parcelize
data class MovieDetailResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Long,
    val genres: List<HashMap<String,String>>,
    val homepage: String,
    val id: Long,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<HashMap<String,String>>,
    val production_countries: List<HashMap<String,String>>,
    val release_date: String,
    val revenue: Long,
    val runtime: String,
    val spoken_languages: List<HashMap<String,String>>,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Float,
    val vote_count: Long
): Parcelable