package com.example.movieapp.domein.repository


import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.data.dto.CastingResponseModelItem
import com.example.movieapp.data.dto.DetailsResponseModelItem
import com.example.movieapp.data.dto.NowPlayingResponseModelItem
import com.example.movieapp.data.dto.PopularResponseModelItem
import com.example.movieapp.data.dto.RecommendationResponseModelItem
import com.example.movieapp.data.dto.ReviewsResponseModelItem
import com.example.movieapp.data.dto.TopRatedResponseModelItem
import com.example.movieapp.data.dto.TrailerResponseModelItem
import com.example.movieapp.data.local.db.FavoriteDAO
import com.example.movieapp.data.local.db.FavoriteDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class movieRepo @Inject constructor(
    private val api: MovieApi,
    private val favoriteDAO: FavoriteDAO,
) {

    fun getNowPlayingData(): Flow<Resource<NowPlayingResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getNowPlayingApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }
    fun getCredits(id:Int) : Flow<Resource<CastingResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieCredits(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    fun getTopRatedData(): Flow<Resource<TopRatedResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getTopRatedApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    fun getPopular(): Flow<Resource<PopularResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getPopularApi()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    fun getDetails(id:Int) : Flow<Resource<DetailsResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieDetail(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    fun getSearchData(query: String): Flow<Resource<TopRatedResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getSearch(query)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    fun getRecommendations(id: Int): Flow<Resource<RecommendationResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieRecommendations(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    fun getReviews(id: Int): Flow<Resource<ReviewsResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieReviews(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }


    fun getTrailers(id: Int): Flow<Resource<TrailerResponseModelItem>> = flow {
        emit(Resource.Loading)
        val response = api.getMovieTrailer(id)
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.message ?: "Unknown error occurred"))

    }

    suspend fun addFav(product: FavoriteDTO) {
        favoriteDAO.addFav(product)
    }

    suspend fun deleteFav(product: FavoriteDTO) {
        favoriteDAO.deleteFav(product)
    }

    suspend fun getFavorites(): Flow<Resource<List<FavoriteDTO>>> = flow {
        emit(Resource.Loading)
        val response = favoriteDAO.getFav()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error("Error"))
    }

}
