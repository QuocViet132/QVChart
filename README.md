# QVChart

# RoundedPieChartView

A customizable rounded pie chart view for Android written in Kotlin.
![PieChart](https://github.com/QuocViet132/QVChart/blob/master/pie_chart.webp?raw=true)
![ProgressBar](https://github.com/QuocViet132/QVChart/blob/master/progressbar.webp?raw=true)

## Setup

1. Add JitPack to your `settings.gradle`:

```kotlin
maven { url 'https://jitpack.io' }
```

2 . Add Gradle app
```
dependencies {
    implementation 'com.github.QuocViet132:QVChart:v1.0.0'
}
```

## Pie Chart
### XML Layout
```
<com.library.chart.piechart.RoundedPieChartView
    android:id="@+id/pieChart"
    android:layout_width="230dp"
    android:layout_height="230dp"
    android:layout_marginTop="40dp" />
```

### Activity
```
val list = listOf<Float>(10f, 30f, 50f, 10f)
val colors = listOf<Int>(
    Color.parseColor("#F97316"),
    Color.parseColor("#F8AD34"),
    Color.parseColor("#10B981"),
    Color.parseColor("#0E6EED"),
    Color.parseColor("#CBD5E1")
)

binding.pieChart.setSegmentSpacing(12f)
binding.pieChart.setRoundCap(true)
binding.pieChart.setThick(40f)
binding.pieChart.setColor(colors)
binding.pieChart.setData(list)
```

## ProgressBar
### XML Layout
```
<com.library.chart.seekbar.MultiColorProgressBar
    android:id="@+id/mcProgress"
    android:layout_width="match_parent"
    android:layout_height="10dp" />
```

### Activity
```
val segments = listOf(
    Pair(50f, Color.parseColor("#6366F1")),
    Pair(50f, Color.parseColor("#F67E7E"))
)

binding.mcProgress.setSegments(segments)
```
