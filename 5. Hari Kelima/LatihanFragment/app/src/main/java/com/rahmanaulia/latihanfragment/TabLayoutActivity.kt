package com.rahmanaulia.latihanfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rahmanaulia.latihanfragment.adapter.TabAdapter
import com.rahmanaulia.latihanfragment.fragment.AddFragment
import com.rahmanaulia.latihanfragment.fragment.DivideFragment
import com.rahmanaulia.latihanfragment.fragment.MinusFragment
import com.rahmanaulia.latihanfragment.fragment.MultipleFragment
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        initTabLayout()
    }

    private fun initTabLayout() {
        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.setData(AddFragment(), "Add")
        tabAdapter.setData(MinusFragment(), "Minus")
        tabAdapter.setData(DivideFragment(), "Divide")
        tabAdapter.setData(MultipleFragment(), "Multiple")

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
