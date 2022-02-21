package com.example.pics.adapers.pics.view_holders

import android.app.Activity
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.pics.R
import com.example.pics.databinding.ListItemFullPicBinding
import com.example.pics.domain.model.Pic
import com.example.pics.utils.getScreenHeightWithoutStatusBar
import com.example.pics.utils.setOnDebouncedClickListener
import com.squareup.picasso.Picasso

class FullPicPageViewHolder(
    view: View,
    private val seeOtherPics: () -> Unit
) : BasePicsViewHolder(view) {

    private val binding: ListItemFullPicBinding = ListItemFullPicBinding.bind(view)
    override val ivPic: AppCompatImageView = binding.picContent.ivPic
    override val tvAuthor: AppCompatTextView = binding.picContent.tvAuthor

    private val height = (view.context as Activity).getScreenHeightWithoutStatusBar() -
        itemView.resources.run {
            getDimension(R.dimen.pic_margin_top) +
                getDimension(R.dimen.pic_margin_bottom) +
                getDimension(R.dimen.button_margin_top) +
                getDimension(R.dimen.button_height) +
                getDimension(R.dimen.button_margin_bottom)
        }.toInt()

    override fun bind(pic: Pic) {
        super.bind(pic)

        if (pic.downloadUrl.isNotEmpty()) {
            Picasso.get()
                .load(pic.downloadUrl)
                .centerCrop(Gravity.CENTER)
                .resize(width, height)
                .into(ivPic)
        }

        binding.otherPics.setOnDebouncedClickListener {
            seeOtherPics.invoke()
        }
    }
}
