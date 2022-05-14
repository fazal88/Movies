package com.androidvoyage.movies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

/**
 * Created by Fazal on 14/05/22.
 */

@BindingAdapter("setMoviePoster")
fun ImageView.setMoviePoster(item: String?) {
    try {
        item?.let {
            Glide.with(this.context)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2$item")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
@BindingAdapter("setLikeVote")
fun TextView.setLikeVote(item: Float?) {
    try {
        item?.let {
            text = "${(item*10).roundToInt()}%\nLiked."
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("setGenre")
fun TextView.setGenre(list: List<HashMap<String,String>>?) {
    try {
        list?.let {
            var builderText = "Genre\n"
            list.forEachIndexed() { index,element ->
                builderText += element["name"].toString()
                if(index < it.size-1){
                    builderText += ", "
                }
            }
            text = builderText
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun setPageEndListener(recyclerView: RecyclerView, callNextPage: () -> Unit) {
    val scrollDirectionDown = 1 // Scroll down is +1, up is -1.
    recyclerView.addOnScrollListener(
        object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(
                        scrollDirectionDown
                    ) && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    callNextPage()
                }
            }
        })
}
