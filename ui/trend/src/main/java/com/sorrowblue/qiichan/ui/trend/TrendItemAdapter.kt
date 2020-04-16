package com.sorrowblue.qiichan.ui.trend

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.sorrowblue.qiichan.domains.item.TrendItem
import com.sorrowblue.qiichan.ui.item.ItemFragmentArgs
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter
import com.sorrowblue.qiichan.ui.trend.databinding.TrendRecyclerViewItemMainBinding
import com.sorrowblue.qiichan.ui.trend.databinding.TrendRecyclerViewItemMainLoadingBinding
import com.sorrowblue.qiichan.ui.user.UserFragmentArgs
import com.sorrowblue.qiichan.ui.view.navigate

class TrendItemAdapter :
	DataBindAdapter<TrendItem?, DataBindAdapter.ViewHolder<TrendItem?, out ViewDataBinding>>() {

	init {
		currentList = List(7) { null }
	}

	override fun areItemsTheSame(old: TrendItem?, new: TrendItem?) = old?.id == new?.id
	override fun areContentsTheSame(old: TrendItem?, new: TrendItem?) = old == new
	override fun getItemViewType(position: Int): Int = if (currentList[position] == null) 0 else 1
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) LoadingViewModel(parent) else ViewHolder(parent)

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<TrendItem?, TrendRecyclerViewItemMainBinding>(
			parent, R.layout.trend_recycler_view_item_main
		) {
		override fun bind(value: TrendItem?, position: Int) {
			if (value == null) return
			binding.item = value
			binding.root.navigate(
				TrendFragmentDirections.actionToItemNavigation(),
				ItemFragmentArgs(trendItem = value).toBundle()
			)
			binding.profileImageUrl.navigate(
				TrendFragmentDirections.actionToUserNavigation(),
				UserFragmentArgs(author = value.author).toBundle()
			)
			binding.executePendingBindings()
		}
	}


	class LoadingViewModel(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<TrendItem?, TrendRecyclerViewItemMainLoadingBinding>(
			parent, R.layout.trend_recycler_view_item_main_loading
		) {
		override fun bind(value: TrendItem?, position: Int) = Unit
	}
}
