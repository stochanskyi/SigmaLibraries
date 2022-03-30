package com.stochanskyi.librariesdemo.presentaiton.feature.locationupdate.jobs

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkRequest
import com.stochanskyi.librariesdemo.presentaiton.data.workmanager.JobDefinition

class UpdateLocationJobDefinition : JobDefinition {
    override val tag: String = "loc_update"

    override val jobRequest: WorkRequest = createWorkRequest()

    private fun createWorkRequest(): WorkRequest {
        return OneTimeWorkRequestBuilder<UpdateLocationWorker>()
            .addTag(tag)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
    }
}