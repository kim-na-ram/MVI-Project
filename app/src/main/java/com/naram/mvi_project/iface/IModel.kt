package com.naram.mvi_project.iface

import androidx.lifecycle.LiveData
import kotlinx.coroutines.channels.Channel

interface IModel<S: IState, I: IIntent, SE: ISideEffect> {
    val intents: Channel<I>
    val sideEffect: Channel<SE>
    val state: LiveData<S>
}