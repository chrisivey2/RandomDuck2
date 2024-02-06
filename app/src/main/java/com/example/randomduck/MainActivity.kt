package com.example.randomduck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.randomduck.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity()
{

    lateinit var binding:ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)
        binding.btnNextImage.setOnClickListener {
            loadImage()
        }
        loadImage()
    }

    private fun loadImage() {
        val url = "https://random-d.uk/api/v2/random"
        Picasso.get().load(R.drawable.loading).into(binding.imageView)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response -> // Parse the response and load image using Picasso
                val imageUrl = response.getString("url")
                Picasso.get().load(imageUrl).placeholder(R.drawable.loading).into(binding.imageView)
            }, { error -> // Handle errors
                error.printStackTrace()
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}