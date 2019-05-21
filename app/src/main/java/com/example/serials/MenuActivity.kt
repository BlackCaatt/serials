package com.example.serials

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.serials.FirebaseHelper
import com.example.serials.ValueEventListenerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MenuActivity : BaseActivity(1) {
    private val TAG = "MenuActivity"
    private lateinit var mFirebase: FirebaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setupBottomNavigation()
        Log.d(TAG, "onCreate")

        mFirebase = FirebaseHelper(this)

        mFirebase.database.child("serials")
            .addValueEventListener(ValueEventListenerAdapter{
                val posts = it.children.map { it.getValue(Serials::class.java)!! }
                Log.d(TAG, "feedPosts: ${posts.joinToString("\n", "\n")}")
            })


    }
}
data class Serials(val name:String = "", val image: String = "")