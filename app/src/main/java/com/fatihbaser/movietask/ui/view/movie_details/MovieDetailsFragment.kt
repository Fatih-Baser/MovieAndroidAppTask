package com.fatihbaser.movietask.ui.view.movie_details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fatihbaser.movietask.R
import com.fatihbaser.movietask.auxiliar.ex_activity_context.addChip
import com.fatihbaser.movietask.auxiliar.ex_activity_context.loadImage
import com.fatihbaser.movietask.auxiliar.ex_activity_context.toast
import com.fatihbaser.movietask.data.model.entity.Actor
import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.data.model.entity.Review
import com.fatihbaser.movietask.databinding.FragmentMovieDetailsBinding
import com.fatihbaser.movietask.ui.view.movie_details.adapters.ActorAdapter
import com.fatihbaser.movietask.ui.view.movie_details.adapters.ReviewAdapter
import com.fatihbaser.movietask.ui.view.movies.adapters.MovieAdapter
import com.fatihbaser.movietask.ui.viewmodel.movie_details.MovieDetailsViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(),
    View.OnClickListener,
    MovieAdapter.OnItemClickListener,
    ReviewAdapter.OnItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieIdNavArgs by navArgs<MovieDetailsFragmentArgs>()
    private val viewModel: MovieDetailsViewModel by viewModels<MovieDetailsViewModel>()
    private lateinit var movieCastAdapter: ActorAdapter
    private lateinit var similarMoviesAdapter: MovieAdapter
    private lateinit var movieReviewsAdapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater)
        initView()
        return binding.root
    }

    private fun initView() {
        initLiveData()
        binding.rVMovieCast.layoutManager =
            LinearLayoutManager(
                this.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        // Similar Movies
        binding.rVSimilarMovies.layoutManager =
            LinearLayoutManager(
                this.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        // Movie Reviews
        binding.rVMovieReviews.layoutManager =
            LinearLayoutManager(
                this.requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        // onCreate viewModel
        viewModel.onCreate(movieIdNavArgs.movieIdSelected)
        // Listeners
        binding.tBMovies.tBImgVTeamDetBackicon.setOnClickListener(this)
        binding.tBMovies.bEditPerfilTBFavMovie.setOnClickListener(this)
        binding.refreshMovieInfo.setOnRefreshListener(this)
        // Observer to the youtube video, in order to auto adapt the video to the lifecycle changes of the fragment
        viewLifecycleOwner.lifecycle.addObserver(binding.yPVideo)
        // Setting the rating bar to not be clickable by the user
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.rBMovieDtRating.focusable = View.NOT_FOCUSABLE
        }
    }

    private fun initLiveData() {
        viewModel.setProgressVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.pgsBMovieDetails.visibility = View.VISIBLE
                } else {
                    binding.pgsBMovieDetails.visibility = View.GONE
                }
            }
        )
        viewModel.showInfoMessage.observe(
            this,
            Observer {
                this.requireActivity().toast(it)
            }
        )
        viewModel.goToMoviesFragment.observe(
            this,
            Observer {
                if (it == true) {
                    this.requireActivity().onBackPressedDispatcher.onBackPressed()
                    viewModel.navigationComplete()
                }
            }
        )
        viewModel.goToNewMovieInfoFragment.observe(
            this,
            Observer {
                if (it != null) {
                    if (it > 0) {
                        findNavController()
                            .navigate(
                                MovieDetailsFragmentDirections
                                    .actionMovieDetailsFragmentSelf(it)
                            )
                        viewModel.navigationSimilarMovieCompleted()
                    }
                }
            }
        )
        //// Refresh
        viewModel.refreshVisibility.observe(
            this,
            Observer {
                binding.refreshMovieInfo.isRefreshing = it
            }
        )
        /// Fill data
        // Add movie to favorite state
        viewModel.setFavButtonIcon.observe(
            this,
            Observer {
                binding.tBMovies.bEditPerfilTBFavMovie.setImageResource(it)
            }
        )
        // Movie name on toolbar
        viewModel.setToolbarTitleText.observe(
            this,
            Observer {
                binding.tBMovies.tVEditPerfilTBTitle.text = it
            }
        )
        // Movie name
        viewModel.setTitleText.observe(
            this,
            Observer {
                binding.tVMoviecVTitle.text = it
            }
        )
        // Release date
        viewModel.setDateText.observe(
            this,
            Observer {
                binding.tVMoviecVDate.text = it
            }
        )
        // Adult class
        viewModel.setAdultContentVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVMoviecV18Content.visibility = View.VISIBLE
                } else {
                    binding.tVMoviecV18Content.visibility = View.GONE
                }
            }
        )
        // Video id
        viewModel.setVideoYoutubeId.observe(
            this,
            Observer {
                binding.yPVideo.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(it, 0f)
                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {
                        youTubePlayer.loadVideo(it, 0f)
                        super.onError(youTubePlayer, error)
                    }
                })
            }
        )
        // Video visibility
        viewModel.setVideoYoutubeVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.yPVideo.visibility = View.VISIBLE
                } else {
                    binding.yPVideo.visibility = View.GONE
                }
            }
        )
        // Video internet error
        viewModel.setYoutubeVideoErrorVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVYoutubeVideoMSG.visibility = View.VISIBLE
                } else {
                    binding.tVYoutubeVideoMSG.visibility = View.GONE
                }
            }
        )
        // Genres Chips
        viewModel.setGenreChipData.observe(
            this,
            Observer { genres ->
                // Delete all the chips on the ChipGroup
                binding.cGGenres.removeAllViews()
                // Adding the chips
                genres.forEach { genre ->
                    genre.name?.let {
                        binding.cGGenres.addChip(
                            this.requireContext(),
                            it
                        )
                    }
                }
            }
        )
        // Poster img
        viewModel.setPosterImgUrl.observe(
            this,
            Observer {
                binding.imgVMovieDtPoster.loadImage(it, false)
            }
        )
        // Overview
        viewModel.setOverviewText.observe(
            this,
            Observer {
                binding.tVOverview.text = it
            }
        )
        // Rating
        viewModel.setRatingText.observe(
            this,
            Observer {
                binding.tVMovieDtRating.text = it
            }
        )
        viewModel.setRatingBarValue.observe(
            this,
            Observer {
                binding.rBMovieDtRating.rating = it
            }
        )
        //// RecyclerView Data
        // Movie Cast internet error
        viewModel.setMovieCastDataErrorVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVNoMovieCastFound.visibility = View.VISIBLE
                } else {
                    binding.tVNoMovieCastFound.visibility = View.GONE
                }
            }
        )
        // Movie Cast
        viewModel.movieCastData.observe(
            this,
            Observer {
                sendMovieCastToAdapter(it)
            }
        )
        // Similar Movies internet error
        viewModel.setSimilarMoviesDataErrorVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVNoSimilarMoviesFound.visibility = View.VISIBLE
                } else {
                    binding.tVNoSimilarMoviesFound.visibility = View.GONE
                }
            }
        )
        // Similar Movies
        viewModel.similarMoviesData.observe(
            this,
            Observer {
                sendSimilarMoviesToAdapter(it)
            }
        )
        // Movie Reviews internet error
        viewModel.setMovieReviewsDataErrorVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVNoMovieReviewsFound.visibility = View.VISIBLE
                } else {
                    binding.tVNoMovieReviewsFound.visibility = View.GONE
                }
            }
        )
        // Movie Reviews
        viewModel.movieReviewsData.observe(
            this,
            Observer {
                sendMovieReviewsToAdapter(it)
            }
        )
    }

    // Movie Cast
    private fun sendMovieCastToAdapter(
        castList: List<Actor>
    ) {
        movieCastAdapter = ActorAdapter(
            castList
        )
        binding.rVMovieCast.adapter = movieCastAdapter
    }

    // Similar Movies
    private fun sendSimilarMoviesToAdapter(
        similarMovies: List<Movie>
    ) {
        similarMoviesAdapter = MovieAdapter(
            similarMovies,
            this
        )
        binding.rVSimilarMovies.adapter = similarMoviesAdapter
    }

    // Movie Reviews
    private fun sendMovieReviewsToAdapter(
        movieReviews: List<Review>
    ) {
        movieReviewsAdapter = ReviewAdapter(
            movieReviews,
            this,
            this.requireContext()
        )
        binding.rVMovieReviews.adapter = movieReviewsAdapter
    }

    override fun onRefresh() {
        viewModel.onRefresh(movieIdNavArgs.movieIdSelected)
    }

    override fun onClick(v: View?) {
        val idSelected: Int = v!!.id
        when (idSelected) {
            // Toolbar
            binding.tBMovies.tBImgVTeamDetBackicon.id -> viewModel.onBackClicked()
            // Favorite Movie
            binding.tBMovies.bEditPerfilTBFavMovie.id -> viewModel.onFavoriteClicked(movieIdNavArgs.movieIdSelected)
        }
    }

    override fun onItemClicked(selectedItem: Int, movieIDSelected: String) {
        viewModel.onSimilarMovieClicked(movieIDSelected)
    }

    override fun onItemClicked(selectedItem: Int, reviewSelected: Review) {
        this.requireActivity().toast("Review by: ${reviewSelected.author.toString()}")
    }
}