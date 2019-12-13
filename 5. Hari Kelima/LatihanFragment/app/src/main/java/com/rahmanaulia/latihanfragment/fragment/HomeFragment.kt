package com.rahmanaulia.latihanfragment.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanaulia.latihanfragment.MainActivity
import com.rahmanaulia.latihanfragment.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionBar()
        onClick()
    }

    private fun initActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Home"
    }

    private fun onClick() {
        btnAdd.setOnClickListener {
            val addFragment =
                AddFragment()
            val bundle = Bundle()
            bundle.putString(AddFragment.EXTRA_ADD,"Add")
            addFragment.arguments = bundle
            (activity as MainActivity).replaceFragment(addFragment)
        }

        btnMinus.setOnClickListener {
            val minusFragment =
                MinusFragment()
            val bundle = Bundle()
            bundle.putString(MinusFragment.EXTRA_MINUS,"Minus")
            minusFragment.arguments = bundle
            (activity as MainActivity).replaceFragment(minusFragment)
        }

        btnDivide.setOnClickListener {
            val divideFragment =
                DivideFragment()
            val bundle = Bundle()
            bundle.putString(DivideFragment.EXTRA_DIVIDE, "Divide")
            divideFragment.arguments = bundle
            (activity as MainActivity).replaceFragment(divideFragment)
        }

        btnMultiple.setOnClickListener {
            val multipleFragment =
                MultipleFragment()
            val bundle = Bundle()
            bundle.putString(MultipleFragment.EXTRA_MULTIPLE, "Multiple")
            multipleFragment.arguments = bundle
            (activity as MainActivity).replaceFragment(multipleFragment)
        }
    }

}
