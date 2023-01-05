package com.naram.data.source

import com.naram.data.entity.SearchResult
import com.naram.data.remote.NaverService
import javax.inject.Inject

class RemoteSourceImpl
@Inject constructor(
    private val naverService: NaverService
): RemoteSource {
    override suspend fun getSearchMovieFlow(keyword: String): SearchResult {
        return naverService.getSearchMovieFlow(keyword)
    }
}