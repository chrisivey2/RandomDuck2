package com.example.randomduck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.randomduck.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity()
{


    lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {

            var email=binding.etUsername.text.toString()
            var passwod=binding.etPassword.text.toString()


            if(email.isBlank() || passwod.isBlank())
            {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show()
            }
            else
            {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email,passwod)
                    .addOnCompleteListener {

                        if(it.isSuccessful)
                        {
                            startActivity(Intent(this,MainActivity::class.java))
                            Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()
                        }
                        else{

                            Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                        }

                    }


            }
        }


    }


}