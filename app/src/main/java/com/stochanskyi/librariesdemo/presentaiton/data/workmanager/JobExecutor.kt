package com.stochanskyi.librariesdemo.presentaiton.data.workmanager

import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import javax.inject.Inject

interface JobExecutor {
    fun execute(definition: JobDefinition)
    suspend fun isJobRunning(tag: String): Boolean
    fun stopJob(tag: String)
}

class JobExecutorImpl @Inject constructor(
    private val workManager: WorkManager
) : JobExecutor {

    override fun execute(definition: JobDefinition) {
        workManager.enqueue(definition.jobRequest)
    }

    override suspend fun isJobRunning(tag: String): Boolean {
        val jobInfos = workManager.getWorkInfosByTag(tag).await()
        return jobInfos.any { it.state == WorkInfo.State.RUNNING || it.state == WorkInfo.State.ENQUEUED }
    }

    override fun stopJob(tag: String) {
        workManager.cancelAllWorkByTag(tag)
    }
}