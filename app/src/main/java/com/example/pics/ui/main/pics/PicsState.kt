package com.example.pics.ui.main.pics

import com.example.pics.domain.model.Pic
import com.example.pics.ui.ScreenState

data class PicsState(
    var screenState: ScreenState = ScreenState.INITIAL,
    var pics: List<Pic> = listOf()
)
