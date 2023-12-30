package com.suitmedia.suitmediaapp.view.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.suitmediaapp.data.response.DataItem
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

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        binding.swiperefresh.setOnRefreshListener {
            viewModel.refreshData()
        }

        viewModel.isRefreshing.observe(this) { isRefreshing ->
            binding.swiperefresh.isRefreshing = isRefreshing
        }
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

            adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataItem) {
                    val uname = data.firstName + " " + data.lastName
                    val intent = Intent()
                    intent.putExtra(EXTRA_USERNAME, uname)
                    setResult(RESULT_CODE, intent)
                    finish()
                }
            })
        }
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val RESULT_CODE = 200
    }
}