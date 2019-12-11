package com.rahmanaulia.latihanintent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClick()
    }

    private fun onClick() {
        btnPindahActivity.setOnClickListener {
            val pindahIntent = Intent(this, PindahActivity::class.java)
            startActivity(pindahIntent)
        }

        btnPindahActivityDenganData.setOnClickListener {
            val pindahDenganDataIntent = Intent(this, PindahDenganDataActivity::class.java)
            pindahDenganDataIntent.putExtra(PindahDenganDataActivity.EXTRA_NAMA, "Aulia Rahman")
            pindahDenganDataIntent.putExtra(PindahDenganDataActivity.EXTRA_UMUR, 23)
            pindahDenganDataIntent.putExtra(PindahDenganDataActivity.EXTRA_EMAIL, "auliar68@gmail.com")
            startActivity(pindahDenganDataIntent)
        }

        btnPindahActivityDenganObject.setOnClickListener {
            val user = User(
                "Aulia Rahman",
                23,
                "auliar68@gmail.com"
            )
            val pindahDenganObjectIntent = Intent(this, PindahDenganObjectActivity::class.java)
            pindahDenganObjectIntent.putExtra(PindahDenganObjectActivity.EXTRA_USER, user)
            startActivity(pindahDenganObjectIntent)
        }

        btnDialNumber.setOnClickListener {
            val phoneNumber = "08123456789"
            val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialPhoneIntent)
        }

        btnPindahActivityDenganResult.setOnClickListener {
            val pindahResultIntent = Intent(this, PindahResultActivity::class.java)
            startActivityForResult(pindahResultIntent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                val selectedValue = data?.getIntExtra(PindahResultActivity.EXTRA_SELECTED_VALUE, 0)
                val text = "Result : $selectedValue"
                tvHasil.text = text
            }
        }
    }
}
