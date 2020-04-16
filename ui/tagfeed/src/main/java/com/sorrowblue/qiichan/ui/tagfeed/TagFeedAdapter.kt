package com.sorrowblue.qiichan.ui.tagfeed

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.sorrowblue.qiichan.domains.item.QiitaSimpleItem
import com.sorrowblue.qiichan.ui.item.ItemFragmentArgs
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter.ViewHolder
import com.sorrowblue.qiichan.ui.tag.TagFragmentArgs
import com.sorrowblue.qiichan.ui.view.navigate
import com.sorrowblue.qiichan.ui.tagfeed.databinding.TagfeedRecyclerViewItemLoadingBinding as ItemLoadingBinding
import com.sorrowblue.qiichan.ui.tagfeed.databinding.TagfeedRecyclerViewItemMainBinding as ItemBinding

class TagFeedAdapter :
	DataBindAdapter<QiitaSimpleItem?, ViewHolder<QiitaSimpleItem?, out ViewDataBinding>>() {

	init {
		currentList = List(7) { null }
	}

	override fun areItemsTheSame(old: QiitaSimpleItem?, new: QiitaSimpleItem?) = old?.id == new?.id
	override fun areContentsTheSame(old: QiitaSimpleItem?, new: QiitaSimpleItem?) = old == new
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) LoadingViewHolder(parent) else ViewHolder(parent)

	override fun getItemViewType(position: Int) = if (currentList[position] == null) 0 else 1

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaSimpleItem?, ItemBinding>(
			parent, R.layout.tagfeed_recycler_view_item_main
		) {
		override fun bind(value: QiitaSimpleItem?, position: Int) {
			if (value == null) return
			binding.item = value
			binding.tagIcon.navigate(
				TagFeedFragmentDirections.actionToTagNavigation(),
				TagFragmentArgs(tagId = value.tag.id.value).toBundle()
			)
			binding.root.navigate(
				TagFeedFragmentDirections.actionToItemNavigation(),
				ItemFragmentArgs(simpleItem = value).toBundle()
			)
			binding.executePendingBindings()
		}
	}

	inner class LoadingViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaSimpleItem?, ItemLoadingBinding>(
			parent, R.layout.tagfeed_recycler_view_item_loading
		) {
		override fun bind(value: QiitaSimpleItem?, position: Int) = Unit
	}
}
