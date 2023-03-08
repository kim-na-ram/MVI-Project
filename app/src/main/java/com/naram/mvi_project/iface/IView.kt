package com.naram.mvi_project.iface

/**
 * 앱의 상태를 전달 받아 화면에 랜더링
 */
interface IView<S: IState, SE: ISideEffect> {
    // 랜더링할 하나의 상태를 허용
    // intent() 를 Observable 하여 유저의 반응에 응답
    fun render(state: S)
    fun navigate(from: String)
}