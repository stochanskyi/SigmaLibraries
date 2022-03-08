package com.stochanskyi.librariesdemo.domain.features.activityrecognition

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.ActivityUpdate
import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.DisposableFlow
import javax.inject.Inject

interface ObserveActivityUpdateUseCase {
    suspend operator fun invoke(interval: Long): Result<DisposableFlow<ActivityUpdate>>
}

class ObserveActivityUpdateUseCaseImpl @Inject constructor(
    private val activityRecognitionRepository: ActivityRecognitionRepository
) : ObserveActivityUpdateUseCase {

    override suspend fun invoke(interval: Long): Result<DisposableFlow<ActivityUpdate>> {
        return activityRecognitionRepository.getActivityUpdateFlow(interval)
    }

}