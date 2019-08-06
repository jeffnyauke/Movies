package io.piestack.movies.ui.main

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.piestack.movies.BuildConfig
import io.piestack.movies.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun bindView(movieModel: Movie) {
        itemView.textMovieTitle.text = "${layoutPosition+1}. ${movieModel.title}"
        itemView.textMovieRating.text = "Rating: ${movieModel.voteAverage}"
        itemView.textReleaseDate.text = "Release Date: ${movieModel.releaseDate}"

        movieModel.posterPath?.let {
            Glide.with(itemView.context).load(BuildConfig.TMDB_POSTER_API + it).into(itemView.imageMovie)
        }

    }

}