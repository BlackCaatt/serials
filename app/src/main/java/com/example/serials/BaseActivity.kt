package com.example.serials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
<<<<<<< HEAD
import kotlinx.android.synthetic.main.bottom_navigation_view.*
=======
import kotlinx.android.synthetic.main.bottom_nagivation_view.*
>>>>>>> parent of c0ce342... Revert "idk"

abstract class BaseActivity(val navNumber:Int) : AppCompatActivity() {
    private val TAG ="BaseActivity"
    fun setupBottomNavigation() {
        bottom_navigation_view.setTextVisibility(false)
        bottom_navigation_view.enableAnimation(false)
        for(i in 0 until bottom_navigation_view.menu.size())
        {
            bottom_navigation_view.setIconTintList(i, null)
        }
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            val nextActivity =
                when(it.itemId) {
                    R.id.nav_item_home -> MainActivity::class.java
                    R.id.nav_item_menu -> MenuActivity::class.java
                    R.id.nav_item_search -> SearchActivity::class.java
                    else ->{
                        Log.e(TAG, "unknown van item $it")
                        null
                    }
                }
            if (nextActivity != null){
                val intent = Intent(this, nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0,0)
                true
            }
            else {
                false
            }
        }
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true
    }
}