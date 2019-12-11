package com.rahmanaulia.latihanview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        btn3Points.setOnClickListener {
            score += 3
            tvScore.text = score.toString()
        }

        btn2Points.setOnClickListener {
            score += 2
            tvScore.text = score.toString()
        }
    }
}
