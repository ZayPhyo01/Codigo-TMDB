package com.codigo.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.codigo.appbase.vos.ReturnResult
import com.codigo.tmdb.R
import com.codigo.tmdb.activities.MovieDetailActivity
import com.codigo.tmdb.activities.MovieDetailType
import com.codigo.tmdb.adapters.MovieListAdapter
import com.codigo.tmdb.components.itemdecorators.SpacingItemGridItemDecorator
import com.codigo.tmdb.databinding.FragmentPopularMovieListBinding
import com.codigo.tmdb.ui.viewmodels.HomeMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMovieListFragment : BaseFragment() {

    companion object {
        fun newInstance(): PopularMovieListFragment {
            return PopularMovieListFragment()
        }
    }

    val homeMovieViewModel: HomeMovieViewModel by activityViewModels()
    lateinit var binding: FragmentPopularMovieListBinding
    lateinit var movieListAdapter: MovieListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_popular_movie_list,
                container,
                false
            )
        return binding.root
    }

   

    override fun initView() {

        binding.rvPopularMovie.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPopularMovie.addItemDecoration(
            SpacingItemGridItemDecorator(
                requireContext(),
                resources.getDimension(R.dimen.margin_small).toInt()
            )
        )
        movieListAdapter = MovieListAdapter(requireContext(), { id, isFavourite ->
            homeMovieViewModel.onTapPopularMovieFavourite(id, isFavourite)
        }, {
            startActivity(
                MovieDetailActivity.newIntent(
                    requireContext(),
                    type = MovieDetailType.MOVIE_POPULAR,
                    id = it
                )
            )
        })

        binding.rvPopularMovie.adapter = movieListAdapter
        setUpPopularListListener()
    }

    private fun setUpPopularListListener() {
        homeMovieViewModel.popularlistLD.observe(this) {
            if (it is ReturnResult.PositiveResult) {
                movieListAdapter.setNewData(it.data.toMutableList())
                loadingDialog.dismiss()
            }else if (it is ReturnResult.Loading) {
                loadingDialog.show()
            }else {
                handleError(it)
                loadingDialog.dismiss()
            }
        }
    }
}