package com.suitmedia.suitmediaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suitmedia.suitmediaapp.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        binding.tvName.text = name

        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this, ThirdScreenActivity::class.java))
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}