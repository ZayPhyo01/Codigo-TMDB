package com.codigo.tmdb.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.codigo.appbase.adapters.BaseListAdapter
import com.codigo.tmdb.R
import com.codigo.tmdb.databinding.ItemViewMovieBinding

import com.codigo.tmdb.viewholders.MovieViewHolder
import com.codigo.tmdb.vos.MovieVO

class MovieListAdapter(c: Context, private val onClickFavourite : (String, Boolean) -> Unit , private val onClickDetail: (String) -> Unit) :
    BaseListAdapter<MovieViewHolder, MovieVO>(c, callback = object :
        DiffUtil.ItemCallback<MovieVO>() {
        override fun areItemsTheSame(oldItem: MovieVO, newItem: MovieVO): Boolean {
            return oldItem.id == newItem.id && oldItem.isFavourite == newItem.isFavourite
        }

        override fun areContentsTheSame(oldItem: MovieVO, newItem: MovieVO): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemViewMovieBinding =
            DataBindingUtil.inflate(
                mLayoutInflater,
                R.layout.item_view_movie,
                parent,
                false
            )
        return MovieViewHolder(binding , onClickFavourite ,onClickDetail)
    }
}