package com.example.mysuitmediaintern.ui

import android.app.Application
import androidx.lifecycle.ViewModel

class SharedViewModel(application: Application) : ViewModel() {
    var inputName: String? = null
}