package com.stochanskyi.librariesdemo.presentaiton.data.workmanager

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import timber.log.Timber
import java.util.*
import javax.inject.Inject

interface JobExecutor {
    fun execute(definition: JobDefinition): UUID
    suspend fun isJobRunning(tag: String): Boolean
    fun stopJob(tag: String)
    fun observeJobState(id: UUID): LiveData<WorkInfo>
}

class JobExecutorImpl @Inject constructor(
    private val workManager: WorkManager
) : JobExecutor {

    override fun execute(definition: JobDefinition): UUID {
        val request = definition.jobRequest
        workManager.enqueue(request)

        Timber.d("Job ${request.id} enqueued")
        return request.id
    }

    override suspend fun isJobRunning(tag: String): Boolean {
        val jobInfos = workManager.getWorkInfosByTag(tag).await()
        return jobInfos.any { it.state == WorkInfo.State.RUNNING || it.state == WorkInfo.State.ENQUEUED }
    }

    override fun stopJob(tag: String) {
        Timber.d("Job with tag $tag stopped")
        workManager.cancelAllWorkByTag(tag)
    }

    override fun observeJobState(id: UUID): LiveData<WorkInfo> {
        return workManager.getWorkInfoByIdLiveData(id)
    }
}