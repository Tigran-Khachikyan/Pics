package com.example.pics.adapers.pics.view_holders

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pics.R
import com.example.pics.domain.model.Pic
import com.example.pics.utils.getScreenWidth

abstract class BasePicsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    protected abstract val ivPic: AppCompatImageView
    protected abstract val tvAuthor: AppCompatTextView

    protected val width: Int by lazy {
        view.run {
            val screenWidth = (context as Activity).getScreenWidth()
            screenWidth - 2 * resources.getDimension(R.dimen.pic_margin_horizontal).toInt()
        }
    }

    open fun bind(pic: Pic) {
        tvAuthor.text = pic.author
    }
}
