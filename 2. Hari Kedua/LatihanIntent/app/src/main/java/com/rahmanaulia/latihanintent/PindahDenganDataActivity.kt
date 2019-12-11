package com.rahmanaulia.latihanintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pindah_dengan_data.*

class PindahDenganDataActivity : AppCompatActivity() {

    companion object{
        //Tipe data String
        const val EXTRA_NAMA = "extra_nama"
        //Tipe data Integer
        const val EXTRA_UMUR = "extra_umur"
        //Tipe data String
        const val EXTRA_EMAIL = "extra_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pindah_dengan_data)

        getData()
    }

    private fun getData() {
        if (intent != null){
            val nama = intent.getStringExtra(EXTRA_NAMA)
            val umur = intent.getIntExtra(EXTRA_UMUR, 0)
            val email = intent.getStringExtra(EXTRA_EMAIL)

            tvNama.text = nama
            tvUmur.text = umur.toString()
            tvEmail.text = email
        }
    }
}
