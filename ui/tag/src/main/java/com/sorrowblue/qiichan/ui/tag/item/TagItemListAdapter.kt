package com.sorrowblue.qiichan.ui.tag.item

import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.ui.item.ItemFragmentArgs
import com.sorrowblue.qiichan.ui.item.ItemTagAdapter
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter
import com.sorrowblue.qiichan.ui.tag.R
import com.sorrowblue.qiichan.ui.tag.databinding.TagRecyclerViewItemListItemBinding
import com.sorrowblue.qiichan.ui.tag.databinding.TagRecyclerViewItemListItemLoadingBinding

internal class TagItemListAdapter :
	DataBindAdapter<QiitaItem?, DataBindAdapter.ViewHolder<QiitaItem?, out ViewDataBinding>>() {
	override fun areItemsTheSame(old: QiitaItem?, new: QiitaItem?) = old?.id == new?.id
	override fun areContentsTheSame(old: QiitaItem?, new: QiitaItem?) = old == new

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) LoadingViewHolder(parent) else ViewHolder(parent)

	override fun getItemViewType(position: Int) = if (currentList[position] == null) 0 else 1

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaItem?, TagRecyclerViewItemListItemBinding>(
			parent, R.layout.tag_recycler_view_item_list_item
		) {
		override fun bind(value: QiitaItem?, position: Int) {
			if (value == null) return
			binding.item = value
			binding.root.setOnClickListener {
				it.findNavController()
					.navigate(
						R.id.item_navigation,
						ItemFragmentArgs(value).toBundle()
					)
			}
			binding.tagsFrame.adapter = ItemTagAdapter().also {
				it.currentList = value.tags
			}
			binding.profileImageUrl.setOnClickListener {
				it.findNavController()
					.navigate("qiichan://qiichan.sorrowblue.com/users/${value.user.userId.value}".toUri())
			}
		}
	}

	class LoadingViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaItem?, TagRecyclerViewItemListItemLoadingBinding>(
			parent, R.layout.tag_recycler_view_item_list_item_loading
		) {
		override fun bind(value: QiitaItem?, position: Int) = Unit
	}
}
