package com.naram.mvi_project.iface

interface IView<S: IState, SE: ISideEffect> {
    fun render(state: S)
    fun navigate(from: String)
}