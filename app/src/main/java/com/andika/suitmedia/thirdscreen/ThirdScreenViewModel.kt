// ThirdScreenViewModel.kt
package com.andika.suitmedia.thirdscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andika.suitmedia.api.ApiClient
import com.andika.suitmedia.api.ApiService
import com.andika.suitmedia.data.User
import com.andika.suitmedia.data.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel : ViewModel() {

    private val apiService: ApiService = ApiClient.apiService
    val userListLiveData: MutableLiveData<List<User>> = MutableLiveData()
    private var currentPage = 1
    private var totalPage = Int.MAX_VALUE // Set an initial value indicating no limit
    internal var isLoading = false

    fun loadInitialData() {
        if (!isLoading && currentPage <= totalPage) {
            isLoading = true
            apiService.getUsers(currentPage, 6).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        if (userResponse != null) {
                            val newData = userResponse.data
                            totalPage = userResponse.total_pages
                            currentPage++

                            userListLiveData.postValue(newData)
                        }
                    }
                    isLoading = false
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    // Handle failure
                    isLoading = false
                }
            })
        }
    }

    fun loadMoreData() {
        loadInitialData()
    }
}

