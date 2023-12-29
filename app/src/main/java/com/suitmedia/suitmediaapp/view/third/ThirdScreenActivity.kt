package com.suitmedia.suitmediaapp.view.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.databinding.ActivityThirdScreenBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserData()
    }

    private fun setUserData() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingAdapter {
                adapter.retry()
            }
        )
        viewModel.getUsers().observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}