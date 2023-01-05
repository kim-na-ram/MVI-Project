package com.naram.data

import com.naram.data.source.RemoteSource
import com.naram.data.mapper.MovieMapper
import com.naram.domain.model.Movie
import com.naram.domain.repo.Repository
import javax.inject.Inject

class RepositoryImpl
@Inject constructor(
    private val remoteSource: RemoteSource
): Repository {
    override suspend fun getRepos(keyword: String): List<Movie> {
        return MovieMapper.resultToMovie(remoteSource.getSearchMovieFlow(keyword))
    }
}