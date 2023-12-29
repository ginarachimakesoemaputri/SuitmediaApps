package com.suitmedia.suitmediaapp.view.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.databinding.ActivityFirstScreenBinding
import com.suitmedia.suitmediaapp.view.second.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding
    private val viewModel: FirstScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val name = binding.edtName.text.toString()
            if (name.isEmpty()){
                binding.edtName.error = getString(R.string.empty_input)
            } else {
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.EXTRA_NAME, name)
                startActivity(intent)
            }
        }

        binding.btnCheck.setOnClickListener {
            val palindromeText = binding.edtPalindrome.text.toString()
            if (palindromeText.isEmpty()){
                binding.edtPalindrome.error = getString(R.string.empty_input)
            } else {
                viewModel.checkPalindrome(palindromeText)
                val result = viewModel.isPalindrome
                if (result) {
                    showToast(getString(R.string.is_palindrome))
                } else {
                    showToast(getString(R.string.not_palindrome))
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}