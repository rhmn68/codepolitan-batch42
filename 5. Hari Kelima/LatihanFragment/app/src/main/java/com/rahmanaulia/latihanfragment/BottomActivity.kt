package com.rahmanaulia.latihanfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rahmanaulia.latihanfragment.fragment.AddFragment
import com.rahmanaulia.latihanfragment.fragment.DivideFragment
import com.rahmanaulia.latihanfragment.fragment.MinusFragment
import com.rahmanaulia.latihanfragment.fragment.MultipleFragment
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_add -> {
                    replaceFragment(AddFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_minus ->{
                    replaceFragment(MinusFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_divide -> {
                    replaceFragment(DivideFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_multiple -> {
                    replaceFragment(MultipleFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }

        bottomNavigation.selectedItemId = R.id.action_add
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainerBottomNavigation, fragment)
            .addToBackStack(null)
            .commit()
    }
}
