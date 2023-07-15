package com.example.mysuitmediaintern.ui.third

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysuitmediaintern.api.ApiConfig
import com.example.mysuitmediaintern.api.ApiResponse
import com.example.mysuitmediaintern.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenViewModel(application: Application) : ViewModel() {
    private val _listUser = MutableLiveData<List<User>>()
    val listUser: LiveData<List<User>> = _listUser

    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh: LiveData<Boolean> = _isRefresh


    private var page = 1
    private var perPage = 10

    init {
        loadListUser()
    }

    private fun loadListUser() {
        _isRefresh.value = true
        val client = ApiConfig.getApiService().getUsers(page, perPage)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUser.value = responseBody.data
                        _isRefresh.value = false
                    }
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun refreshListUser() {
        page = 1
        loadListUser()
    }


}