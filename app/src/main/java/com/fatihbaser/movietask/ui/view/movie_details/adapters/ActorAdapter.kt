package com.fatihbaser.movietask.ui.view.movie_details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.movietask.auxiliar.ex_activity_context.loadImage
import com.fatihbaser.movietask.data.model.entity.Actor
import com.fatihbaser.movietask.databinding.ActorCardViewBinding


class ActorAdapter (
    private val castList: List<Actor>
): RecyclerView.Adapter<ActorAdapter.ViewHolder>() {
    inner class ViewHolder(
        val binding: ActorCardViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ActorCardViewBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Loading Profile image on the cardview
        castList[position].profileImgUrl?.let { holder.binding.imgVActorCVImg.loadImage(it, false) }
        // Loading Actor Name
        holder.binding.tVActorCVName.text = castList[position].name
        // Loading Character Name
        holder.binding.tVActorCVCharacterName.text = castList[position].characterName
    }

    override fun getItemCount(): Int {
        return castList.size
    }
}