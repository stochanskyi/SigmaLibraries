package com.stochanskyi.librariesdemo.data.biometrics.storage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

interface DemoDataStorage {
    fun getData(): DemoDataDto?
    fun setData(data: DemoDataDto)
}

@OptIn(ExperimentalStdlibApi::class)
class DemoDataStorageImpl(
    private val context: Context,
    private val preferencesName: String,
    private val moshi: Moshi
) : DemoDataStorage {

    companion object {
        private const val DATA_KEY = "data_key"
    }

    private val demoDataAdapter: JsonAdapter<DemoDataDto> by lazy { moshi.adapter() }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    }

    override fun getData(): DemoDataDto? {
        val json = sharedPreferences.getString(DATA_KEY, null)
        return json?.let {
            demoDataAdapter.fromJson(json)
        }
    }

    @SuppressLint("ApplySharedPref")
    override fun setData(data: DemoDataDto) {
        val json = demoDataAdapter.toJson(data)
        sharedPreferences.edit()
            .putString(DATA_KEY, json)
            .commit()
    }

}