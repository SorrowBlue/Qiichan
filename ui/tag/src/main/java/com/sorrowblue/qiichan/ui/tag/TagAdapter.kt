package com.sorrowblue.qiichan.ui.tag

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter
import com.sorrowblue.qiichan.ui.tag.list.TagListFragmentDirections
import com.sorrowblue.qiichan.ui.tag.databinding.TagRecyclerViewItemMainBinding as ItemBinding
import com.sorrowblue.qiichan.ui.tag.databinding.TagRecyclerViewItemMainLoadingBinding as ItemLoadingBinding

internal class TagAdapter :
	DataBindAdapter<QiitaTag?, DataBindAdapter.ViewHolder<QiitaTag?, out ViewDataBinding>>() {

	init {
		currentList = List(8) { null }
	}

	override fun areItemsTheSame(old: QiitaTag?, new: QiitaTag?) = old?.id == new?.id

	override fun areContentsTheSame(old: QiitaTag?, new: QiitaTag?) = old == new
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) LoadingViewHolder(parent) else ViewHolder(parent)

	override fun getItemViewType(position: Int) = if (currentList[position] == null) 0 else 1

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaTag?, ItemBinding>(
			parent, R.layout.tag_recycler_view_item_main
		) {
		override fun bind(value: QiitaTag?, position: Int) {
			binding.tag = value
			binding.root.setOnClickListener {
				it.findNavController().navigate(
					TagListFragmentDirections.tagActionTaglistfragmentToTagNavigation().actionId,
					TagFragmentArgs(tag = value).toBundle()
				)
			}
		}

	}

	class LoadingViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaTag?, ItemLoadingBinding>(
			parent, R.layout.tag_recycler_view_item_main_loading
		) {
		override fun bind(value: QiitaTag?, position: Int) = Unit
	}
}
