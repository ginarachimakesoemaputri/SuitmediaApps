package com.suitmedia.suitmediaapp.view.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.suitmedia.suitmediaapp.databinding.ActivitySecondScreenBinding
import com.suitmedia.suitmediaapp.view.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ThirdScreenActivity.RESULT_CODE && result.data != null) {
            val uname = result.data?.getStringExtra(ThirdScreenActivity.EXTRA_USERNAME)
            binding.tvSelectedName.text = uname
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        binding.tvName.text = name

        binding.btnChooseUser.setOnClickListener {
//            startActivity(Intent(this, ThirdScreenActivity::class.java))

            val intentWithResult = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            resultLauncher.launch(intentWithResult)
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}