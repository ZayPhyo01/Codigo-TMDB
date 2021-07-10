package com.codigo.tmdb.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codigo.appbase.network
import com.codigo.tmdb.R
import com.codigo.tmdb.databinding.AcitityMovieDetailBinding
import com.codigo.tmdb.ui.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    lateinit var binding: AcitityMovieDetailBinding

    companion object {
        val MOVIE_TYPE_KEY = "mk"
        val MOVIE_ID_KEY = "idKey"
        fun newIntent(c: Context, id: String, type: String): Intent {
            return Intent(c, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_TYPE_KEY, type)
                putExtra(MOVIE_ID_KEY, id)
            }
        }
    }

    val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.acitity_movie_detail)
        val movieType = intent?.extras!!.getString(MOVIE_TYPE_KEY)!!
        val id = intent?.extras!!.getString(MOVIE_ID_KEY)!!

        setUpMovieDetailListener()

        if (movieType == MovieDetailType.MOVIE_POPULAR) {
            movieDetailViewModel.loadPopularMovieDetailById(id)
            setPopularMovieFavouriteListener(id)
        } else {
            movieDetailViewModel.loadUpComingMovieDetailById(id)
            setUpComingMovieFavouriteListener(id)
        }

    }

    private fun setPopularMovieFavouriteListener(id: String) {
        binding.ivFavourite.setOnClickListener {
            binding?.vo?.let {
                movieDetailViewModel.onTapPopularMovieFavourite(id, !it.isFavourite)
            }

        }
    }

    private fun setUpComingMovieFavouriteListener(id: String) {
        binding.ivFavourite.setOnClickListener {
            binding?.vo?.let {
                movieDetailViewModel.onTapUpComingMovieFavourite(id, !it.isFavourite)
            }
        }
    }

    private fun setUpMovieDetailListener() {
        movieDetailViewModel.movieMovieDetailLD.observe(this) {
            binding.vo = it
            binding.ivMovieDetail.network(it.imageUrl)
            if (it.isFavourite) {
                binding.ivFavourite.setImageResource(R.drawable.ic_favourite)
            } else {
                binding.ivFavourite.setImageResource(R.drawable.ic_unfavourite)
            }
        }
    }
}