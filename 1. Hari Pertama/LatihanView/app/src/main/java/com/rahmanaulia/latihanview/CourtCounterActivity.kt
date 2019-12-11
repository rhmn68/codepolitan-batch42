package com.rahmanaulia.latihanview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_court_counter.*

class CourtCounterActivity : AppCompatActivity() {

    private var scoreTeamA = 0
    private var scoreTeamB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_court_counter)

        processCount()
    }

    private fun processCount() {
        btn3PointsTeamA.setOnClickListener {
            scoreTeamA += 3
            tvScoreTeamA.text = scoreTeamA.toString()
        }

        btn2PointsTeamA.setOnClickListener {
            scoreTeamA += 2
            tvScoreTeamA.text = scoreTeamA.toString()
        }

        btnFreeThrowTeamA.setOnClickListener {
            scoreTeamA += 1
            tvScoreTeamA.text = scoreTeamA.toString()
        }

        btn3PointsTeamB.setOnClickListener {
            scoreTeamB += 3
            tvScoreTeamB.text = scoreTeamB.toString()
        }

        btn2PointsTeamB.setOnClickListener {
            scoreTeamB += 2
            tvScoreTeamB.text = scoreTeamB.toString()
        }

        btnFreeThrowTeamB.setOnClickListener {
            scoreTeamB += 1
            tvScoreTeamB.text = scoreTeamB.toString()
        }

        btnReset.setOnClickListener {
            scoreTeamA = 0
            scoreTeamB = 0
            tvScoreTeamA.text = scoreTeamA.toString()
            tvScoreTeamB.text = scoreTeamB.toString()
        }
    }
}
