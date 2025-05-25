package com.library.chart.piechart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View


class RoundedPieChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND // Conner 2 dau
        strokeWidth = 40f // Circle thickness
    }

    private var colors = listOf(
        Color.parseColor("#F97316"),
        Color.parseColor("#F8AD34"),
        Color.parseColor("#10B981"),
        Color.parseColor("#0E6EED"),
        Color.parseColor("#CBD5E1")
    )

    private var percentageList: List<Float> = emptyList()

    // Set khoảng cách giữa các đoạn
    private var segmentSpacingDegrees = 13f

    /**
     * This function converts the given list to a list of percentages.
     * If any element has a percentage less than 5f, it is fixed at 5% to ensure it can be displayed on the chart.
     * Normalizes the elements in the list so that the sum is 100%.
     * */
    fun setData(rawPercentages: List<Float>) {
        val total = rawPercentages.sum()
        if (total == 0f) return

        val minPercent = 5f
        val normalized = rawPercentages.map { it / total * 100f }

        val adjusted = MutableList(normalized.size) { -1f }
        var fixedTotal = 0f
        val remainingIndices = mutableListOf<Int>()

        for ((index, percent) in normalized.withIndex()) {
            if (percent < minPercent) {
                adjusted[index] = minPercent
                fixedTotal += minPercent
            } else {
                remainingIndices.add(index)
            }
        }

        val remainingSum = remainingIndices.fold(0f) { acc, i -> acc + normalized[i] }
        val remainingPercent = 100f - fixedTotal

        for (index in remainingIndices) {
            val scale = normalized[index] / remainingSum
            adjusted[index] = scale * remainingPercent
        }

        percentageList = adjusted
        invalidate()
    }

    fun setColor(colorInput: List<Int>) {
        colors = colorInput
    }

    fun setSegmentSpacing(value: Float) {
        segmentSpacingDegrees = value
    }

    fun setRoundCap(enabled: Boolean) {
        paint.strokeCap = if (enabled) Paint.Cap.ROUND else Paint.Cap.BUTT
        invalidate()
    }

    fun setThick(value: Float) {
        paint.strokeWidth = value
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (percentageList.isEmpty()) return

        val size = width.coerceAtMost(height)
        val padding = paint.strokeWidth / 2 + 10f
        val rect = RectF(
            padding,
            padding,
            size - padding,
            size - padding
        )

        var startAngle = -90f

        for ((index, percent) in percentageList.withIndex()) {
            val fullSweep = percent / 100f * 360f
            val adjustedSweep = fullSweep - segmentSpacingDegrees

            if (adjustedSweep > 0f) {
                paint.color = colors[index % colors.size]
                canvas.drawArc(rect, startAngle, adjustedSweep, false, paint)
            }

            startAngle += fullSweep
        }

    }
}
