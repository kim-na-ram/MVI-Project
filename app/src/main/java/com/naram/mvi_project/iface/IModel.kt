package com.naram.mvi_project.iface

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

/**
 * 앱의 상태를 표시하는 불변 객체
 */
interface IModel<S: IState, I: IIntent, SE: ISideEffect> {
    val intents: Channel<I>
    val sideEffect: Channel<SE>
    val state: LiveData<S>
}