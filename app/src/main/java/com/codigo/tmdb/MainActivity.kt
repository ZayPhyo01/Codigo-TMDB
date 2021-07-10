package com.codigo.tmdb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codigo.tmdb.databinding.ActivityMainBinding
import com.codigo.tmdb.fragments.PopularMovieListFragment
import com.codigo.tmdb.fragments.UpComingMovieListFragment
import com.codigo.tmdb.ui.viewmodels.HomeMovieViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val homeMovieViewModel: HomeMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        changeFragment(PopularMovieListFragment.newInstance())
        setupButtonNavigation()

    }

    private fun setupButtonNavigation() {
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuPopular -> {
                    changeFragment(PopularMovieListFragment.newInstance())
                    true
                }
                R.id.menuUpComing -> {
                    changeFragment(UpComingMovieListFragment.newInstance())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.homeContainer, fragment).commit()
    }
}