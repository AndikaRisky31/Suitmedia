// ThirdScreenActivity.kt
package com.andika.suitmedia.thirdscreen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andika.suitmedia.databinding.ActivityThirdScreenBinding
import com.andika.suitmedia.databinding.NavbarBinding

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var bindingNavbar: NavbarBinding
    private lateinit var viewModel: ThirdScreenViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        bindingNavbar = NavbarBinding.bind(binding.includeNavbar.root)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ThirdScreenViewModel::class.java]

        setSupportActionBar(bindingNavbar.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        bindingNavbar.titleText.text = "Third Screen"

        bindingNavbar.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        setupRecyclerView()

        // Observe changes in userListLiveData
        viewModel.userListLiveData.observe(this) { newData ->
            userAdapter.updateData(newData)
        }
        viewModel.loadInitialData()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(this,mutableListOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = userAdapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                // Tidak perlu melakukan sesuatu saat status pengguliran berubah
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading && (firstVisibleItem + visibleItemCount == totalItemCount) && (totalItemCount != 0)) {
                    Log.d("LoadMore", "Calling loadMoreData")
                    viewModel.loadMoreData()
                }
            }
        })

    }
}
