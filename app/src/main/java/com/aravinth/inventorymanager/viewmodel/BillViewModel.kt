package com.aravinth.inventorymanager.viewmodel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.navigation.NavController
import com.aravinth.inventorymanager.domain.usecase.BillUseCase

class BillViewModel(application: Application) :
    AndroidViewModel(application)
    {
        private val useCase: BillUseCase
        private val billViewModel: BillViewModel
    }
