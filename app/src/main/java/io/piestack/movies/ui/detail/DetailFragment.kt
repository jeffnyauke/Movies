package io.piestack.movies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import io.piestack.movies.BuildConfig
import io.piestack.movies.R
import io.piestack.movies.util.General
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : DaggerFragment() {

    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_detail, container, false)

        val movie = DetailFragmentArgs.fromBundle(arguments!!).movie
        val position = DetailFragmentArgs.fromBundle(arguments!!).position
        val newPosition = position.toInt() + 1
        v.textMovieTitle.text = "$newPosition. ${movie.title}"
        v.textMovieRating.text = "Rating: ${movie.voteAverage}"
        v.textReleaseDate.text = "Release Date: ${movie.releaseDate}"
        v.textPlotSynopsis.text = movie.overview

        v.imageView.setOnClickListener {
            togglePlay()
        }

        movie.posterPath?.let {
            Glide.with(requireContext()).load(BuildConfig.TMDB_POSTER_API + it).into(v.imageMovie)
        }
        return v
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    private fun togglePlay() {
        if (isPlaying) {
            imageView.background = resources.getDrawable(R.drawable.ic_pause)
        } else {
            isPlaying = true
            General.showYouTubeVideo("YxjovPfuoxA", requireActivity())
        }
    }

}
