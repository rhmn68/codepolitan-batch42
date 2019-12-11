package com.rahmanaulia.latihanview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginProcess()
    }

    private fun loginProcess() {
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            val result = "username: $username, pass: $password"
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

            tvResult.text = result
        }
    }
}
