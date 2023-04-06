package com.energyaustralia.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.energyaustralia.data.api.RetrofitErrorHandler.retrofitErrorHandler
import com.energyaustralia.data.api.model.MusicFestival
import com.energyaustralia.data.repository.MusicFestivalRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val musicFestivalRepo: MusicFestivalRepo
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<MusicFestival>())
    val state: StateFlow<List<MusicFestival>>
        get() = _state
    var errorMessage: String? by mutableStateOf("")

    init {
        viewModelScope.launch {
            try {
                val musicFestivals = retrofitErrorHandler(musicFestivalRepo.getMusicFestivals())
                _state.value = musicFestivals.sortedBy { it.name }
            } catch (e: IllegalStateException) {
                errorMessage = "No records found"
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}