package com.naram.domain.model

interface SearchResult {
    val lastBuildDate: String
    val total: Int
    val start: Int
    val display: Int
    val items: List<Movie>
}