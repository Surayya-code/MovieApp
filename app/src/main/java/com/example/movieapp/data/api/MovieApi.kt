package com.example.movieapp.data.api

import com.example.movieapp.common.utils.Constants.API_KEY
import com.example.movieapp.data.dto.CastingResponseModelItem
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.data.dto.NowPlayingResponseModelItem
import com.example.movieapp.data.dto.PopularResponseModelItem
import com.example.movieapp.data.dto.RecommendationResponseModelItem
import com.example.movieapp.data.dto.ReviewsResponseModelItem
import com.example.movieapp.data.dto.TopRatedResponseModelItem
import com.example.movieapp.data.dto.TrailerResponseModelItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingApi(@Query("api_key") apiKey: String = API_KEY, ): NowPlayingResponseModelItem

    @GET("movie/top_rated")
    suspend fun getTopRatedApi(@Query("api_key") apiKey: String = API_KEY ): TopRatedResponseModelItem

    @GET("movie/popular")
    suspend fun getPopularApi(@Query("api_key") apiKey: String = API_KEY ): PopularResponseModelItem

    @GET("search/movie")
    suspend fun getSearch(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY
    ) : TopRatedResponseModelItem

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): DetailsResponseModelItem

    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): RecommendationResponseModelItem

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): ReviewsResponseModelItem

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TrailerResponseModelItem

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): CastingResponseModelItem

}