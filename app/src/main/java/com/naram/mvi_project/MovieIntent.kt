package com.naram.mvi_project

import com.naram.mvi_project.iface.IIntent

sealed class MovieIntent: IIntent {
    object SearchMovie: MovieIntent()
    object NavigateToMainActivity: MovieIntent()
}
