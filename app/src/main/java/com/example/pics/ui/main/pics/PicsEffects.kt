package com.example.pics.ui.main.pics

import com.example.pics.data.network.Request

sealed class PicsEffects {
    data class ShowError(val error: Request.Error) : PicsEffects()
    object ScrollToSecondPic : PicsEffects()
}
