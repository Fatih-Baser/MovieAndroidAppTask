package com.fatihbaser.movietask.ui.view.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.movietask.auxiliar.ex_activity_context.loadImage
import com.fatihbaser.movietask.data.model.entity.Movie
import com.fatihbaser.movietask.databinding.MovieCardViewBinding


class MovieAdapter(
    private val movieList: List<Movie>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(
        val binding: MovieCardViewBinding
    ) : RecyclerView.ViewHolder(binding.root),
            View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        private fun getSelectedMovie(): String {
            return binding.tVMoviecVId.text.toString()
        }
        override fun onClick(p0: View?) {
            val selectedItem: Int = bindingAdapterPosition
            if (selectedItem != RecyclerView.NO_POSITION) {
                if (getSelectedMovie().isNotEmpty()) {
                    listener.onItemClicked(selectedItem, getSelectedMovie())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MovieCardViewBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Loading Poster image on the cardview
        movieList[position].posterImgURL?.let { holder.binding.imgVMoviecVPoster.loadImage(it, false) }
        // Loading movie rating value
        holder.binding.tVMoviecVRate.text = movieList[position].rating
        // Loading movie title value
        holder.binding.tVMoviecVTitle.text = movieList[position].name
        // Loading movie release date value
        holder.binding.tVMoviecVDate.text = movieList[position].releaseDate
        // Loading movie id value
        holder.binding.tVMoviecVId.text = movieList[position].id.toString()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface OnItemClickListener {
        fun onItemClicked (selectedItem: Int, movieIDSelected: String)
    }
}