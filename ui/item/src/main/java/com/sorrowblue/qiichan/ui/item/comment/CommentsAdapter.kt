package com.sorrowblue.qiichan.ui.item.comment

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.sorrowblue.qiichan.domains.item.comment.QiitaComment
import com.sorrowblue.qiichan.domains.user.UserId
import com.sorrowblue.qiichan.ui.item.R
import com.sorrowblue.qiichan.ui.item.databinding.ItemRecyclerViewItemCommentBinding
import com.sorrowblue.qiichan.ui.item.databinding.ItemRecyclerViewItemCommentLoadingBinding
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter

internal class CommentsAdapter :
	DataBindAdapter<QiitaComment?, DataBindAdapter.ViewHolder<QiitaComment?, out ViewDataBinding>>() {

	var userId: UserId? = null

	init {
		currentList = List(7) { null }
	}

	inner class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaComment?, ItemRecyclerViewItemCommentBinding>(
			parent, R.layout.item_recycler_view_item_comment
		) {
		override fun bind(value: QiitaComment?, position: Int) {
			if (value == null) return
			binding.comment = value
			binding.delete.isVisible = value.user.userId == userId
			binding.itemImageview.isVisible = value.user.userId == userId
			if (value.user.userId == userId) {
				binding.itemImageview.setOnClickListener {
					it.findNavController()
						.navigate(CommentsFragmentDirections.actionToCommentEditorFragment(value))
				}
			}
		}
	}

	class Loading(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaComment?, ItemRecyclerViewItemCommentLoadingBinding>(
			parent, R.layout.item_recycler_view_item_comment_loading
		) {
		override fun bind(value: QiitaComment?, position: Int) = Unit
	}

	override fun areItemsTheSame(old: QiitaComment?, new: QiitaComment?) = old?.id == new?.id

	override fun areContentsTheSame(old: QiitaComment?, new: QiitaComment?) = old == new

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) Loading(parent) else ViewHolder(parent)

	override fun getItemViewType(position: Int) = if (currentList[position] == null) 0 else 1
}
