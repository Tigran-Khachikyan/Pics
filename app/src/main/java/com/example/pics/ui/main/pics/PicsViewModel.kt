package com.example.pics.ui.main.pics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pics.data.network.Request.*
import com.example.pics.data.repositories.PicsRepository
import com.example.pics.ui.ScreenState.*
import com.example.pics.ui.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PicsViewModel(
    application: Application,
    private val repository: PicsRepository
) : AndroidViewModel(application) {
    private val data = PicsState()
    private val _state: MutableLiveData<PicsState> = MutableLiveData()
    val state: LiveData<PicsState>
        get() = _state
    private val _effects: SingleLiveEvent<PicsEffects> = SingleLiveEvent()
    val effects: SingleLiveEvent<PicsEffects>
        get() = _effects

    fun loadPics() {
        _state.value = data.apply { screenState = LOADING }
        viewModelScope.launch {
            val result = repository.getPics()
            withContext(Dispatchers.Main) {
                when (result) {
                    is Success -> {
                        _state.value = data.apply {
                            screenState = FETCHED
                            pics = result.data
                        }
                    }
                    else -> {
                        _state.value = data.apply { screenState = FETCHED }
                        _effects.value = PicsEffects.ShowError(result as Error)
                    }
                }
            }
        }
    }

    fun seeMorePics() {
        if (data.pics.size > 1) {
            _effects.value = PicsEffects.ScrollToSecondPic
        }
    }
}
