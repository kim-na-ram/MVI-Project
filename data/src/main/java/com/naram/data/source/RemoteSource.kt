package com.naram.data.source

import com.naram.data.entity.SearchResult

interface RemoteSource {
    suspend fun getSearchMovieFlow(keyword: String): SearchResult
}