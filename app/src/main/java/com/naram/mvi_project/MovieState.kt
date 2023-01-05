package com.naram.mvi_project

import com.naram.domain.model.Movie
import com.naram.mvi_project.iface.IState

data class MovieState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
): IState