package com.stochanskyi.librariesdemo.domain.features.activityrecognition.models

import kotlinx.coroutines.flow.Flow

interface DisposableFlow<T> : Flow<T> {
    suspend fun dispose()
}

class DisposableFlowImpl<T>(
    private val flow: Flow<T>,
    private val disposeBlock: suspend () -> Unit,
): DisposableFlow<T>, Flow<T> by flow {

    override suspend fun dispose() {
        disposeBlock()
    }
}

@Suppress("FunctionName")
fun <T> DisposableFlow(
    flow: Flow<T>,
    disposeBlock: suspend () -> Unit
) = DisposableFlowImpl(flow, disposeBlock)