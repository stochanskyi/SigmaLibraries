package com.stochanskyi.librariesdemo.domain.features.simplecall

import javax.inject.Inject

private const val DELAY_DURATION = 1000L

interface SampleCallUseCase {
    suspend operator fun invoke(): String
}

class SampleCallUseCaseImpl @Inject constructor(
    private val simpleCallRepository: SimpleCallRepository
) : SampleCallUseCase {

    override suspend operator fun invoke(): String {
        return simpleCallRepository.getSimpleStringWithDelay(DELAY_DURATION)
    }

}