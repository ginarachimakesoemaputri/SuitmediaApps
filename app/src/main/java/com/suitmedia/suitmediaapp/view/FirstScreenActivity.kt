package com.suitmedia.suitmediaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java)
            startActivity(intent)
        }
    }
}