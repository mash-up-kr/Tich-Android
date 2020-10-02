package com.example.tichandroid.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import android.view.animation.Interpolator
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEachIndexed
import com.example.tichandroid.R
import com.mashup.android.base.extension.toPixels
import kotlin.math.sin

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.loadingViewStyle,
    defStyleRes: Int = R.style.Widget_TichComponent_LoadingView
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    companion object {
        private const val LOADING_COUNT = 3
        private const val DELAY = 70
        private const val DURATION = 530
    }

    private val interpolator = SineInterpolator()
    private var indicator: Drawable?

    var indicatorRadius: Int =
        context.resources.getDimensionPixelSize(R.dimen.loading_view_indicator_radius_default)
        set(value) {
            field = value
            updateIndicators()
        }

    var indicatorPadding: Int =
        context.resources.getDimensionPixelSize(R.dimen.loading_view_indicator_padding_default)
        set(value) {
            field = value
            updateIndicators()
        }

    var indicatorTint: ColorStateList? = null
        set(value) {
            field = value
            updateIndicators()
        }

    init {
        orientation = HORIZONTAL
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.LoadingView,
            defStyleAttr,
            defStyleRes
        )
        indicatorRadius = a.getDimensionPixelSize(
            R.styleable.LoadingView_indicatorRadius,
            resources.getDimensionPixelSize(R.dimen.loading_view_indicator_radius_default)
        )
        indicatorPadding = a.getDimensionPixelSize(
            R.styleable.LoadingView_indicatorPadding,
            resources.getDimensionPixelSize(R.dimen.loading_view_indicator_padding_default)
        )
        indicator = a.getDrawable(R.styleable.LoadingView_indicator)
            ?: ResourcesCompat.getDrawable(context.resources, R.drawable.oval, context.theme)
        indicatorTint = a.getColorStateList(R.styleable.LoadingView_indicatorTint)
        a.recycle()

        for (i in 0 until LOADING_COUNT) {
            val view = View(context)
            view.background = indicator
            view.backgroundTintList = indicatorTint
            addView(view, generateLayoutParams(if (i == 0) 0 else indicatorPadding))
        }
        setWillNotDraw(false)
    }

    fun setIndicator(drawable: Drawable) {
        indicator = drawable
        updateIndicators()
    }

    fun getIndicator(): Drawable? = indicator

    private fun updateIndicators() {
        forEachIndexed { index, view ->
            view.background = indicator
            view.backgroundTintList = indicatorTint
            view.layoutParams = generateLayoutParams(if (index == 0) 0 else indicatorPadding)
        }
        invalidate()
        requestLayout()
    }

    private fun generateLayoutParams(left: Int): LayoutParams {
        val radiusPx = context.toPixels(value = indicatorRadius)
        return LayoutParams(indicatorRadius * 2, indicatorRadius * 2).apply {
            topMargin = radiusPx
            bottomMargin = radiusPx
            leftMargin = left
        }
    }

    // TODO draw 반복 시키는 방법에서 ButtonLoadingStateHelper를 이용하도록 변경
    override fun dispatchDraw(canvas: Canvas) {
        val currentTime = SystemClock.elapsedRealtime()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val input = (currentTime - i * DELAY) % DURATION / (1f * DURATION)
            val interpolated = interpolator.getInterpolation(input)
            child.translationY = interpolated * indicatorRadius
        }
        super.dispatchDraw(canvas)
        if (alpha > 0) {
            postInvalidate()
        }
    }

    private class SineInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float =
            sin(input.toDouble() * Math.PI * 2.0).toFloat()
    }
}
