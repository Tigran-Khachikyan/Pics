package com.example.pics.adapers.pics.view_holders

import android.view.Gravity
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.pics.R
import com.example.pics.databinding.ListItemPicBinding
import com.example.pics.domain.model.Pic
import com.squareup.picasso.Picasso

class PicsViewHolder(view: View) : BasePicsViewHolder(view) {

    private val binding: ListItemPicBinding = ListItemPicBinding.bind(view)
    override val ivPic: AppCompatImageView = binding.ivPic
    override val tvAuthor: AppCompatTextView = binding.tvAuthor

    fun bind(pic: Pic, isLastItem: Boolean) {
        super.bind(pic)

        val picHeight = width * pic.height / pic.width
        val max = itemView.resources.getDimension(R.dimen.pic_max_height).toInt()
        val height = if (picHeight > max) max else picHeight

        if (pic.downloadUrl.isNotEmpty())
            Picasso.get()
                .load(pic.downloadUrl)
                .placeholder(R.drawable.placeholder)
                .centerCrop(Gravity.CENTER)
                .resize(width, height)
                .into(ivPic)

        val marginBottom =
            if (isLastItem) R.dimen.pic_margin_bottom_last_item else R.dimen.pic_margin_bottom

        binding.root.run {
            if (layoutParams is MarginLayoutParams) {
                resources.run {
                    (layoutParams as MarginLayoutParams).setMargins(
                        getDimension(R.dimen.pic_margin_horizontal).toInt(),
                        getDimension(R.dimen.pic_margin_top).toInt(),
                        getDimension(R.dimen.pic_margin_horizontal).toInt(),
                        getDimension(marginBottom).toInt(),
                    )
                }
                requestLayout()
            }
        }
    }
}
