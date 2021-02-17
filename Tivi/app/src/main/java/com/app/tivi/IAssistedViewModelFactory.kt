package com.app.tivi

import android.os.Bundle
import androidx.lifecycle.ViewModel

interface IAssistedViewModelFactory<T:ViewModel> {
    fun create(args : Bundle?): T
}