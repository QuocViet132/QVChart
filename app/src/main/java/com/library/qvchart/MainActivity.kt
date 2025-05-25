package com.library.qvchart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.library.qvchart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        handleEvents()
    }

    private fun initView() {

        val listTests = listOf<Float>(10f, 30f, 50f, 10f)
        val colorsTest = listOf<Int>(
            Color.parseColor("#F97316"),
            Color.parseColor("#F8AD34"),
            Color.parseColor("#10B981"),
            Color.parseColor("#0E6EED"),
            Color.parseColor("#CBD5E1")
        )

        binding.pieChart.setColor(colorsTest)
        binding.pieChart.setData(listTests)
        binding.pieChart.setSegmentSpacing(12f)
        binding.pieChart.setRoundCap(true)
        binding.pieChart.setThick(40f)

        val segments = listOf(
            Pair(100f, Color.parseColor("#6366F1")),
            Pair(200f, Color.parseColor("#F67E7E")),
            Pair(300f, Color.parseColor("#CBD5E1"))
        )

        binding.mcProgress.setSegments(segments)
    }

    private fun handleEvents() {
        binding.btnDraw.setOnClickListener {
            if (binding.edtSegment.text.toString().isEmpty()) return@setOnClickListener
            if (binding.edtThick.text.toString().isEmpty()) return@setOnClickListener

            binding.pieChart.setSegmentSpacing(binding.edtSegment.text.toString().toFloat())
            binding.pieChart.setThick(binding.edtThick.text.toString().toFloat())
            binding.pieChart.setRoundCap(true)

            binding.mcProgress.setSpacing(binding.edtSegment.text.toString().toFloat())
        }
    }

}