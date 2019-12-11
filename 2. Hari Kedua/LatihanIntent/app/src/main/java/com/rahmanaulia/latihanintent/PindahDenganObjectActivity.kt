package com.rahmanaulia.latihanintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pindah_dengan_object.*

class PindahDenganObjectActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pindah_dengan_object)

        getData()
    }

    private fun getData() {
        if (intent != null){
            val user = intent.getParcelableExtra<User>(EXTRA_USER)
            if (user != null){
                val text = "Nama: ${user.nama}\n" +
                        "Umur: ${user.umur}\n" +
                        "Email: ${user.email}"

                tvHasilObject.text = text
            }
        }
    }
}
