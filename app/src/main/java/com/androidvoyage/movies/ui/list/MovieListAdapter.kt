package com.androidvoyage.movies.ui.list

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.movies.data.model.MovieItem
import com.androidvoyage.movies.databinding.ItemMovieBinding

open class MovieListAdapter(
    private val clickListener: MovieItemClickListener
) : ListAdapter<MovieItem, MovieListAdapter.ViewHolder>(
    PassbookDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    class PassbookDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem
    }

    class ViewHolder private constructor(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val res = itemView.context.resources

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(
            item: MovieItem,
            clickListener: MovieItemClickListener
        ) {
            binding.item = item
            binding.click = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }

    open class MovieItemClickListener(
        val clickMovieItem: (item: MovieItem?) -> Unit
    ) {
        fun onClickMovieItem(item: MovieItem?) = clickMovieItem(item)
    }

}