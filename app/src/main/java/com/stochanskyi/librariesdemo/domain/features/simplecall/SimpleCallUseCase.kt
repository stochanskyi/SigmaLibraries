package com.stochanskyi.librariesdemo.domain.features.simplecall

import javax.inject.Inject

private const val DELAY_DURATION = 1000L

interface SimpleCallUseCase {
    suspend operator fun invoke(): String
}

class SimpleCallUseCaseImpl @Inject constructor(
    private val simpleCallRepository: SimpleCallRepository
) : SimpleCallUseCase {

    override suspend operator fun invoke(): String {
        return simpleCallRepository.getSimpleStringWithDelay(DELAY_DURATION)
    }

}