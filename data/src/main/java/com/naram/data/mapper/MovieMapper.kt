package com.naram.data.mapper

import com.naram.data.entity.SearchResult
import com.naram.domain.model.Movie

object MovieMapper {
    fun resultToMovie(searchResult: SearchResult): List<Movie> {
        return searchResult.items as List<Movie>
    }
}