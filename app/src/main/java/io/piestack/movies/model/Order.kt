package io.piestack.movies.model

enum class Order(val url: String, val desc:String) {
    Popularity("popularity.desc", "Most Popular"),
    Rating("vote_average.desc", "Highest Rated")
}