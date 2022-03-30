package com.stochanskyi.librariesdemo.presentaiton.data.workmanager

import androidx.work.WorkRequest

interface JobDefinition {
    val tag: String
    val jobRequest: WorkRequest
}