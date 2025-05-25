package com.library.chart.seekbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MultiColorProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progressSegments: List<Pair<Float, Int>> = emptyList()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private var segmentSpacing = 6f

    fun setSegments(segments: List<Pair<Float, Int>>) {
        val total = segments.sumOf { it.first.toDouble() }
        progressSegments = if (total == 0.0) {
            emptyList()
        } else {
            segments.map { (value, color) ->
                Pair(((value / total) * 100f).toFloat(), color)
            }
        }
        invalidate()
    }

    fun setSpacing(value: Float) {
        this.segmentSpacing = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val totalSpacing = segmentSpacing * (progressSegments.size - 1)
        val totalWidth = width.toFloat() - totalSpacing
        var startX = 0f

        for ((index, segment) in progressSegments.withIndex()) {
            val (percent, color) = segment
            val segmentWidth = (percent / 100f) * totalWidth

            paint.color = color

            canvas.drawRoundRect(
                startX,
                0f,
                startX + segmentWidth,
                height.toFloat(),
                height / 2f,
                height / 2f,
                paint
            )

            startX += segmentWidth + segmentSpacing
        }
    }
}