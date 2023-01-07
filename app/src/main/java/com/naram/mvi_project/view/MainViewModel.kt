package com.naram.mvi_project.view

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naram.domain.RepositoriesUseCase
import com.naram.mvi_project.MovieIntent
import com.naram.mvi_project.MovieSideEffect
import com.naram.mvi_project.MovieState
import com.naram.mvi_project.iface.IModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repositoriesUseCase: RepositoriesUseCase
): ViewModel(), IModel<MovieState, MovieIntent, MovieSideEffect> {
    override val intents: Channel<MovieIntent> = Channel(Channel.UNLIMITED)
    override val sideEffect: Channel<MovieSideEffect> = Channel(Channel.UNLIMITED)

    private val _state = MutableLiveData<MovieState>().apply { value = MovieState() }
    override val state: LiveData<MovieState>
        get() = _state

    private val _navigation = MutableStateFlow("")
    val navigation = _navigation

    val keyword = ObservableField("")

    init {
        intentConsumer()
    }

    private fun intentConsumer() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { userIntent ->
                when(userIntent) {
                    MovieIntent.SearchMovie -> fetchData()
                    MovieIntent.NavigateToMainActivity -> sideEffect.send(MovieSideEffect.NavigateToMainActivity)
                }
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true, errorMessage = null) }

                launch {
                    flow {
                        emit(MovieState(repositoriesUseCase.invoke(keyword.get() ?: "null", viewModelScope)))
                    }.collect { state ->
                        if(state.movies.isEmpty()) {
                            updateState {
                                it.copy(isLoading = false, errorMessage = "Do Not Found")
                            }
                        } else {
                            updateState {
                                it.copy(isLoading = false, movies = state.movies, errorMessage = null)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }

    /*
    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState { it.copy(isLoading = true, errorMessage = null) }

                repositoriesUseCase.invoke(keyword.get() ?: "null", viewModelScope) { result ->
                    launch {
                        flow {
                            emit(MovieState(result))
                        }.collect { item ->
                            if(item.movies.isEmpty()) {
                                updateState {
                                    it.copy(isLoading = false, errorMessage = "Do Not Found")
                                }
                            } else {
                                updateState {
                                    it.copy(isLoading = false, movies = item.movies, errorMessage = null)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }
    */

    private suspend fun updateState(handler: suspend (intent: MovieState) -> MovieState) {
        _state.postValue(handler(state.value!!))
    }

    fun searchBtnOnClicked() {
        viewModelScope.launch {
            intents.send(MovieIntent.SearchMovie)
        }
    }
}