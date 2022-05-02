package com.stochanskyi.librariesdemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import java.io.File

class TestView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatTextView(context, attrs, defStyleAttr) {


    private var a: TypedArray? = null

    init {
        val att = context.obtainStyledAttributes(R.styleable.TestView)
    }

    fun s() {
        a?.recycle()

        File("").inputStream()


    }
}