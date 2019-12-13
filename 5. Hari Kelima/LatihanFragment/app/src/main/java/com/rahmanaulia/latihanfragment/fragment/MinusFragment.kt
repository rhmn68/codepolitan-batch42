package com.rahmanaulia.latihanfragment.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanaulia.latihanfragment.MainActivity
import com.rahmanaulia.latihanfragment.R
import kotlinx.android.synthetic.main.fragment_minus.*

class MinusFragment : Fragment() {

    companion object{
        const val EXTRA_MINUS = "extra_minus"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_minus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        processMinus()
    }

    private fun processMinus() {
        btnSubmit.setOnClickListener {
            val number1 = etNumber1.text.toString().toDouble()
            val number2 = etNumber2.text.toString().toDouble()

            val result = number1 - number2
            tvResultAdd.text = result.toString()
        }
    }

    private fun getData() {
        if (arguments != null){
            val title = arguments?.getString(EXTRA_MINUS)

            btnSubmit.text = title
            (activity as MainActivity).supportActionBar?.title = title
        }
    }
}
