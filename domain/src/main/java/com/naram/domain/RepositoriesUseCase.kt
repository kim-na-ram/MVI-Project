package com.naram.domain

import com.naram.domain.model.Movie
import com.naram.domain.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class RepositoriesUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        keyword: String,
        scope: CoroutineScope
    ): List<Movie> {
        val result = scope.async {
            repository.getRepos(keyword)
        }

        return result.await()
    }
}