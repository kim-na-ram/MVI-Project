package com.naram.data.remote

import com.naram.data.entity.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {
    @GET("movie.json?")
    suspend fun getSearchMovieFlow(
        @Query("query") keyword: String
    ): SearchResult
}