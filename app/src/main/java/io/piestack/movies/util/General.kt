package io.piestack.movies.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast

object General {
    val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()
    val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun showYouTubeVideo(videoId: String, activity: Activity) {
        // We aren't embedding a youTube video in 2017. Instead we'll just send
        // users to the youTube app or, failing that, to Chrome.
        if (!TextUtils.isEmpty(videoId)) {
            try {
                val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://$videoId"))
                activity.startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=$videoId"))
                activity.startActivity(webIntent)
            }

        } else {
            Toast.makeText(activity, "Video not valid",
                Toast.LENGTH_LONG).show()
        }
    }
}