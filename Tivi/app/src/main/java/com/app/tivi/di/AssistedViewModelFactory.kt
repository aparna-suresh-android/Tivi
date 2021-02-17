package com.app.tivi.di

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.tivi.utils.IAssistedViewModelFactory

class ViewModelFactory<out V : ViewModel>(
    private val viewModelFactory: IAssistedViewModelFactory<V>,
    private val args: Bundle?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelFactory.create(args) as T
    }

}