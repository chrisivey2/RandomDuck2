package com.example.randomduck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.randomduck.databinding.ActivityLoginBinding
import com.example.randomduck.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity()
{

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {

            val email=binding.etUsername.text.toString()
            val passwod=binding.etPassword.text.toString()

            if(email.isBlank() || passwod.isBlank())
            {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            }
            else
            {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email,passwod)
                    .addOnCompleteListener {

                        if(it.isSuccessful)
                        {
                           var intent= Intent(this,MainActivity::class.java)
                           startActivity(intent)
                        }
                        else{

                            Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                        }

                    }


            }
        }


    }

}