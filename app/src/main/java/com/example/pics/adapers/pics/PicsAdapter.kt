package com.example.pics.adapers.pics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.pics.R
import com.example.pics.adapers.pics.view_holders.BasePicsViewHolder
import com.example.pics.adapers.pics.view_holders.FullPicPageViewHolder
import com.example.pics.adapers.pics.view_holders.PicsViewHolder
import com.example.pics.domain.model.Pic

class PicsAdapter(private val onFetchMorePics: () -> Unit) :
    RecyclerView.Adapter<BasePicsViewHolder>() {

    companion object {
        private const val TYPE_FULL_PAGE = 1
        private const val TYPE_PIC = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_FULL_PAGE else TYPE_PIC
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePicsViewHolder {
        return LayoutInflater.from(parent.context).run {
            when (viewType) {
                TYPE_FULL_PAGE -> FullPicPageViewHolder(
                    inflate(R.layout.list_item_full_pic, parent, false),
                    onFetchMorePics
                )
                else -> PicsViewHolder(inflate(R.layout.list_item_pic, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: BasePicsViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_FULL_PAGE -> (holder as? FullPicPageViewHolder)?.bind(differ.currentList[position])
            else -> (holder as? PicsViewHolder)?.bind(
                pic = differ.currentList[position],
                isLastItem = position == itemCount - 1
            )
        }
    }

    fun submitList(list: List<Pic>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differ: AsyncListDiffer<Pic> = AsyncListDiffer(this, PicsDiffUtil())

    private class PicsDiffUtil : DiffUtil.ItemCallback<Pic>() {
        override fun areItemsTheSame(oldItem: Pic, newItem: Pic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pic, newItem: Pic): Boolean {
            return oldItem == newItem
        }
    }
}
