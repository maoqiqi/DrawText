package com.codearms.maoqiqi.drawtext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.tv)
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100f)
        tv.post { Log.e("MainActivity", "measuredWidth:" + tv.measuredWidth + ",measuredHeight:" + tv.measuredHeight) }
    }
}