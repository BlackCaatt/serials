package com.example.serials

import android.content.Intent
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(0) {
    private val TAG = "MainActivity"
    private lateinit var nAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        Log.d(TAG, "onCreate")

        nAuth = FirebaseAuth.getInstance()
        nAuth.signOut()

    }

    override fun onStart() {
        super.onStart()
        if (nAuth.currentUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
