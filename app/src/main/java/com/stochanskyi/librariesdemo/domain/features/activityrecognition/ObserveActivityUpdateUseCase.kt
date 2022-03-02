package com.stochanskyi.librariesdemo.domain.features.activityrecognition

import com.stochanskyi.librariesdemo.domain.features.activityrecognition.models.ActivityUpdate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveActivityUpdateUseCase {
    operator fun invoke(): Flow<ActivityUpdate>
}

class ObserveActivityUpdateUseCaseImpl @Inject constructor(
    private val activityRecognitionRepository: ActivityRecognitionRepository
) : ObserveActivityUpdateUseCase {
    override fun invoke(): Flow<ActivityUpdate> {
        return activityRecognitionRepository.getActivityUpdateFlow()
    }

}