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
import com.codigo.tmdb.databinding.FragmentUpcomingMovieListBinding
import com.codigo.tmdb.ui.viewmodels.HomeMovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpComingMovieListFragment : BaseFragment() {

    companion object {
        fun newInstance(): UpComingMovieListFragment {
            return UpComingMovieListFragment()
        }
    }

    lateinit var binding: FragmentUpcomingMovieListBinding
    lateinit var upComingMovieAdapter: MovieListAdapter

    val upComingMovieViewModel: HomeMovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_upcoming_movie_list,
            container,
            false
        )
        return binding.root
    }

    override fun initView() {

        upComingMovieAdapter = MovieListAdapter(requireContext(), { id, isFavourite ->
            upComingMovieViewModel.onTapUpComingMovieFavourite(id, isFavourite)
        }, {
            startActivity(
                MovieDetailActivity.newIntent(
                    requireContext(),
                    type = MovieDetailType.MOVIE_UPCOMING,
                    id = it
                )
            )
        })
        setUpUpComingMovieListListener()
        binding.rvUpComingMovie.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvUpComingMovie.addItemDecoration(
            SpacingItemGridItemDecorator(
                requireContext(),
                12
            )
        )
        binding.rvUpComingMovie.adapter = upComingMovieAdapter


    }

    private fun setUpUpComingMovieListListener() {
        upComingMovieViewModel.upComingListLD.observe(this) {
            if (it is ReturnResult.PositiveResult) {
                loadingDialog.dismiss()
                upComingMovieAdapter.setNewData(it.data.toMutableList())
            }
            if (it is ReturnResult.Loading) {
                loadingDialog.show()
            }

        }
    }
}