package com.naram.mvi_project

import com.naram.mvi_project.iface.ISideEffect

sealed class MovieSideEffect: ISideEffect {
    object NavigateToMainActivity: MovieSideEffect()
}
