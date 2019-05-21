package com.example.serials

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MenuActivity : BaseActivity(1) {
    private val TAG = "MenuActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setupBottomNavigation()
        Log.d(TAG, "onCreate")

    }
}
