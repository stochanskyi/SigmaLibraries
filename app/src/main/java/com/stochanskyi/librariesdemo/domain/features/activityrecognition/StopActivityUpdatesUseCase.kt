package com.stochanskyi.librariesdemo.domain.features.activityrecognition

import com.stochanskyi.librariesdemo.data.repository.activityrecognition.ActivityRecognitionRepository
import javax.inject.Inject

interface StopActivityUpdatesUseCase {
    operator fun invoke()
}

class StopActivityUpdatesUseCaseImpl @Inject constructor(
    private val activityRecognitionRepository: ActivityRecognitionRepository
) : StopActivityUpdatesUseCase {
    override fun invoke() {
        activityRecognitionRepository.stopActivityUpdate()
    }
}