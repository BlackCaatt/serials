package com.example.serials

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn.setOnClickListener{
            performRegister()
        }
    }

    private fun performRegister() {
        val email = email_input.text.toString()
        val password = password_input.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста введите почту/пароль", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Log.d("Main", "Successfully created")
            }
            .addOnFailureListener{
                Toast.makeText(this, "Ошибка в данных", Toast.LENGTH_SHORT).show()
                Log.d("Main", "Failed to create user")
            }
    }
}

