package com.ban.jetpackcomposepokedex.feature.base

import androidx.lifecycle.ViewModel
import com.ban.jetpackcomposepokedex.redux.*
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S: State, A: Action>(
    initialViewState: S,
    reducer: Reducer<S, A>,
    middlewares: List<Middleware<S, A>>
) : ViewModel() {

    protected val store = Store(
        initialState = initialViewState,
        reducer = reducer,
        middlewares = middlewares,
    )

    val viewState: StateFlow<S> = store.state
}