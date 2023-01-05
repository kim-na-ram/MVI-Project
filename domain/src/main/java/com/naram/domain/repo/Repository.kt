package com.naram.domain.repo

import com.naram.domain.model.Movie

interface Repository {
    suspend fun getRepos(keyword: String): List<Movie>
}