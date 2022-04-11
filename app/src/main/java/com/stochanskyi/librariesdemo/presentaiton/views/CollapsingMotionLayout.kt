package com.stochanskyi.librariesdemo.presentaiton.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.appbar.AppBarLayout

class CollapsingMotionLayout : MotionLayout, AppBarLayout.OnOffsetChangedListener {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }

        override fun onDetachedFromWindow() {
        (parent as? AppBarLayout)?.removeOnOffsetChangedListener(this)
        super.onDetachedFromWindow()
    }
}