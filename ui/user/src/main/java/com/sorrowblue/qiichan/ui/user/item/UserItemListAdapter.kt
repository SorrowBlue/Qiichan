package com.sorrowblue.qiichan.ui.user.item

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.ui.item.ItemFragmentArgs
import com.sorrowblue.qiichan.ui.item.ItemTagAdapter
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter
import com.sorrowblue.qiichan.ui.user.R
import com.sorrowblue.qiichan.ui.user.databinding.UserRecyclerViewItemItemBinding
import com.sorrowblue.qiichan.ui.user.databinding.UserRecyclerViewItemItemLoadingBinding

internal class UserItemListAdapter :
	PagedListAdapter<QiitaItem, DataBindAdapter.ViewHolder<QiitaItem, out ViewDataBinding>>(
		object : DiffUtil.ItemCallback<QiitaItem>() {
			override fun areItemsTheSame(oldItem: QiitaItem, newItem: QiitaItem) =
				oldItem.id == newItem.id

			override fun areContentsTheSame(oldItem: QiitaItem, newItem: QiitaItem) =
				oldItem == newItem
		}
	) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) LoadingViewHolder(parent) else ViewHolder(parent)

	override fun getItemViewType(position: Int) = if (getItem(position) == null) 0 else 1
	override fun onBindViewHolder(
		holder: DataBindAdapter.ViewHolder<QiitaItem, out ViewDataBinding>,
		position: Int
	) {
		getItem(position)?.let {
			(holder as ViewHolder).bind(it, position)
		}
	}

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaItem, UserRecyclerViewItemItemBinding>(
			parent, R.layout.user_recycler_view_item_item
		) {
		override fun bind(value: QiitaItem, position: Int) {
			binding.item = value
			binding.recycler.adapter = ItemTagAdapter().also {
				it.currentList = value.tags
			}
			binding.root.setOnClickListener {
				it.findNavController()
					.navigate(
						UserItemListFragmentDirections.actionToItemNavigation().actionId,
						ItemFragmentArgs(item = value).toBundle()
					)
			}
		}
	}

	class LoadingViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaItem, UserRecyclerViewItemItemLoadingBinding>(
			parent, R.layout.user_recycler_view_item_item_loading
		) {
		override fun bind(value: QiitaItem, position: Int) = Unit
	}
}
