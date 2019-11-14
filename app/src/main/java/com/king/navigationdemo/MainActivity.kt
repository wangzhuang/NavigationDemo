package com.king.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val HOME_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val PERSON_FRAGMENT = 2
    }

    private var homeFragment: NavController? = null
    private var secondFragment: NavController? = null
    private var personFragment: NavController? = null
    private var fragmentList = mutableListOf<ConstraintLayout>()
    private var showFragment = 0
    private var isBack = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationController()
        initTab()
    }

    private fun initTab() {
        fragmentList.add(home_fragment_layout)
        fragmentList.add(second_fragment_layout)
        fragmentList.add(person_fragment_layout)
        //设置导航栏菜单项Item选中监听
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    showFragmentNavigation(HOME_FRAGMENT)
                }
                R.id.secondFragment ->{
                    showFragmentNavigation(SECOND_FRAGMENT)
                }
                R.id.personFragment ->{
                    showFragmentNavigation(PERSON_FRAGMENT)
                }
            }
            true
        }
    }

    private fun showFragmentNavigation(index: Int) {
        showFragment = index
        fragmentList.forEachIndexed { i, layout ->
            layout.visibility = if (i == index) {
                View.VISIBLE
            } else {
                View.GONE
            }

        }
    }

    private fun initNavigationController() {
        homeFragment = Navigation.findNavController(this, R.id.home_fragment)
        val bundle = bundleOf("name" to "我是传过来的值1")
        homeFragment?.navigate(R.id.action_nav_home_frament_to_nav_second_fragment, bundle)
        secondFragment = Navigation.findNavController(this, R.id.second_fragment)
        val bundle2 = bundleOf("name" to "我是传过来的值2")
        secondFragment?.navigate(R.id.action_nav_second_fragment, bundle2)
        personFragment = Navigation.findNavController(this, R.id.person_fragment)
    }
    override fun onBackPressed() {
        when (showFragment) {
            HOME_FRAGMENT -> {
                isBack = homeFragment?.popBackStack() ?: false
            }
            SECOND_FRAGMENT -> {
                isBack = secondFragment?.popBackStack() ?: false
            }
            PERSON_FRAGMENT -> {
                isBack = personFragment?.popBackStack() ?: false
            }
        }
        if (!isBack) {
            super.onBackPressed()
        }

    }
}
