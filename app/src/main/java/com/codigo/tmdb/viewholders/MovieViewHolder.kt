package com.codigo.tmdb.viewholders

import com.appbase.viewholders.BaseViewHolder
import com.codigo.appbase.network
import com.codigo.tmdb.R
import com.codigo.tmdb.databinding.ItemViewMovieBinding

import com.codigo.tmdb.vos.MovieVO

class MovieViewHolder(
    private val binding: ItemViewMovieBinding,
    private val onClickFavourite: (String, Boolean) -> Unit,
    private val onClickDetail: (String) -> Unit
) :
    BaseViewHolder<MovieVO>(binding.root) {
    override fun setData(data: MovieVO) {
        binding.vo = data

        if (data.isFavourite) {
            binding.ivFavourite.setImageResource(R.drawable.ic_favourite)
        } else {
            binding.ivFavourite.setImageResource(R.drawable.ic_unfavourite)
        }
        data.imageUrl.let {
            binding.ivMovieItem.network(it)
        }


        binding.ivFavourite.setOnClickListener {
            onClickFavourite.invoke(data.id, !data.isFavourite)
        }
        itemView.setOnClickListener {
            onClickDetail.invoke(data.id)
        }
    }
}