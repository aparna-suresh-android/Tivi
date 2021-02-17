package com.app.tivi.utils

import android.os.Bundle
import androidx.lifecycle.ViewModel

interface IAssistedViewModelFactory<T:ViewModel> {
    fun create(args : Bundle?): T
}