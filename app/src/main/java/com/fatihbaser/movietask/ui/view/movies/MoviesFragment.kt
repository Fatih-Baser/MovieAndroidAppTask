package com.fatihbaser.movietask.ui.view.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fatihbaser.movietask.R
import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.databinding.FragmentMoviesBinding
import com.fatihbaser.movietask.ui.view.movies.adapters.MovieAdapter
import com.fatihbaser.movietask.ui.viewmodel.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(),
    MovieAdapter.OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentMoviesBinding

    private val viewModel: MoviesViewModel by viewModels<MoviesViewModel>()

    // User Favorite Movies
    private lateinit var userFavoriteMoviesAdapter: MovieAdapter

    // Now Playing Movies
    private lateinit var nowPlayingMovieAdapter: MovieAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        initLiveData()
        binding.rVUserFavoriteMovies.layoutManager = LinearLayoutManager(
            this.requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rVNowPlayingMovies.layoutManager = LinearLayoutManager(
            this.requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewModel.onCreate()
        binding.refreshMovies.setOnRefreshListener(this)
    }
    private fun initLiveData() {
        viewModel.setProgressVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.pgsBMovies.visibility = View.VISIBLE
                } else {
                    binding.pgsBMovies.visibility = View.GONE
                }
            }
        )
        viewModel.goToMovieInfoFragment.observe(
            this,
            Observer {
                if (it != null) {
                    if (it > 0) {
                        findNavController()
                            .navigate(
                                MoviesFragmentDirections
                                    .actionMoviesFragmentToMovieDetailsFragment(it)
                            )
                        viewModel.navigationCompleted()
                    }
                }
            }
        )
        viewModel.refreshVisibility.observe(
            this,
            Observer {
                binding.refreshMovies.isRefreshing = it
            }
        )
        viewModel.userFavoriteMoviesData.observe(
            this,
            Observer {
                sendUserFavoriteMoviesToAdapter(it)
            }
        )
        viewModel.nowPlayingMoviesData.observe(
            this,
            Observer {
                sendNowPlayingMoviesAdapter(it)
            }
        )
        viewModel.setNoFavoriteMoviesVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVNoFavoriteMovies.visibility = View.VISIBLE
                } else {
                    binding.tVNoFavoriteMovies.visibility = View.GONE
                }
            }
        )
        viewModel.setNoNowPlayingMoviesVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVNoNowPlayingMovies.visibility = View.VISIBLE
                } else {
                    binding.tVNoNowPlayingMovies.visibility = View.GONE
                }
            }
        )
    }

    private fun sendUserFavoriteMoviesToAdapter(
        moviesList: List<Movie>
    ) {
        userFavoriteMoviesAdapter = MovieAdapter(
            moviesList,
            this
        )
        binding.rVUserFavoriteMovies.adapter = userFavoriteMoviesAdapter
    }

    private fun sendNowPlayingMoviesAdapter(
        moviesList: List<Movie>
    ) {
        nowPlayingMovieAdapter = MovieAdapter(
            moviesList,
            this
        )
        binding.rVNowPlayingMovies.adapter = nowPlayingMovieAdapter
    }

    override fun onItemClicked(selectedItem: Int, movieIDSelected: String) {
        viewModel.onMovieClicked(movieIDSelected)
    }

    override fun onRefresh() {
        viewModel.onRefresh()
    }

}