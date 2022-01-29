package com.stochanskyi.librariesdemo.data.repository.simplecall

import com.stochanskyi.librariesdemo.data.network.simplecall.SimpleCallApi
import com.stochanskyi.librariesdemo.domain.features.simplecall.SimpleCallRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class SimpleCallRepositoryImpl @Inject constructor(
    private val simpleCallApi: SimpleCallApi
) : SimpleCallRepository {

    override suspend fun getSimpleStringWithDelay(delayDuration: Long): String {
        delay(delayDuration)

        return simpleCallApi.requestString()
    }

}