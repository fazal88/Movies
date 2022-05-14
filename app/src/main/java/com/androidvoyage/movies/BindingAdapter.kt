package com.androidvoyage.movies

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.movies.data.model.MovieItem
import com.androidvoyage.movies.ui.list.MovieListAdapter
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay
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

@BindingAdapter("setInVisible")
fun View.setInVisible(isInvisible: Boolean?) {
    try {
        isInvisible?.let {
            visibility = if(it) View.INVISIBLE else View.VISIBLE
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter(value = ["clickItem", "clickListener"], requireAll = true)
fun View.clickElement(clickItem: MovieItem?,clickListener : MovieListAdapter.MovieItemClickListener) {
    try {
        onClickWithAnimation {
            clickListener.onClickMovieItem(clickItem,this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@BindingAdapter("onClickWithAnimation")
fun View.onClickWithAnimation(clickFunction: () -> Unit) {
    this.setOnClickListener {
        setClickedAnimation(this, object : OnClickedAnimationListener {
            override fun onAnimationEnd() {
                clickFunction()
            }
        })
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

var scaleAnimation: Animation? = null

fun setClickedAnimation(view: View, onClickedAnimationListener: OnClickedAnimationListener?) {

    val animation: Animation? = getScaleAnimation(view.context)
    view.startAnimation(animation)

    animation?.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {

        }

        override fun onAnimationEnd(animation: Animation) {
            onClickedAnimationListener?.onAnimationEnd()
        }

        override fun onAnimationRepeat(animation: Animation) {

        }
    })

}

fun getScaleAnimation(context: Context?): Animation? {

    if (scaleAnimation == null) {
        scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_up_down)
    }

    return scaleAnimation
}

fun View.setOnClickAnimateListener(clickListener: View.OnClickListener) {
    this.setOnClickListener {
        val animation: Animation? = getScaleAnimation(this.context)
        this.startAnimation(animation)

        animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                clickListener.onClick(this@setOnClickAnimateListener)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

}

interface OnClickedAnimationListener {

    fun onAnimationEnd()
}
