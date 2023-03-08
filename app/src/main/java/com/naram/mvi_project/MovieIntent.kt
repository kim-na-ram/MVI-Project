package com.naram.mvi_project

import com.naram.mvi_project.iface.IIntent

sealed class MovieIntent: IIntent {
    // 영화를 검색하는 Intent
    object SearchMovie: MovieIntent()
    // 메인으로 돌아가려는 Intent
    object NavigateToMainActivity: MovieIntent()
}
