package com.stochanskyi.librariesdemo.data.maps

import android.content.Context
import androidx.annotation.RawRes
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.stochanskyi.librariesdemo.data.maps.models.CountriesBoundsDto
import okio.buffer
import okio.source
import java.io.InputStream
import javax.inject.Inject

interface CountryBoundsDataSource {
    fun getCountriesBounds(): CountriesBoundsDto
}

@OptIn(ExperimentalStdlibApi::class)
class CountryBoundsDataSourceImpl @Inject constructor(
    private val context: Context,
    @RawRes private val fileId: Int,
    moshi: Moshi
) : CountryBoundsDataSource {

    private val adapter: JsonAdapter<CountriesBoundsDto> = moshi.adapter()

    override fun getCountriesBounds(): CountriesBoundsDto {
        return adapter.fromJson(getFileStream().source().buffer()) ?: CountriesBoundsDto.EMPTY
    }

    private fun getFileStream(): InputStream {
        return context.resources.openRawResource(fileId)
    }

}
