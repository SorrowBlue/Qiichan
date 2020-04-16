package com.sorrowblue.qiichan.ui.user.follow

import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sorrowblue.qiichan.domains.user.QiitaUser
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter
import com.sorrowblue.qiichan.ui.user.R
import com.sorrowblue.qiichan.ui.user.databinding.UserRecyclerViewItemUserBinding
import com.sorrowblue.qiichan.ui.user.databinding.UserRecyclerViewItemUserLoadingBinding

internal class UserListAdapter : PagedListAdapter<QiitaUser, RecyclerView.ViewHolder>(object :
	DiffUtil.ItemCallback<QiitaUser>() {
	override fun areItemsTheSame(oldItem: QiitaUser, newItem: QiitaUser) =
		oldItem.userId == newItem.userId

	override fun areContentsTheSame(oldItem: QiitaUser, newItem: QiitaUser) =
		newItem == oldItem

}) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		if (viewType == 0) LoadingViewHolder(
			parent
		) else ViewHolder(
			parent
		)

	override fun getItemViewType(position: Int) = getItem(position)?.let { 1 } ?: 0

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaUser?, UserRecyclerViewItemUserBinding>(
			parent, R.layout.user_recycler_view_item_user
		) {
		override fun bind(value: QiitaUser?, position: Int) {
			if (value == null) return
			binding.user = value
			binding.root.setOnClickListener {
				it.findNavController()
					.navigate("qiichan://qiichan.sorrowblue.com/users/${value.userId.value}".toUri())
			}
		}
	}

	class LoadingViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaUser?, UserRecyclerViewItemUserLoadingBinding>(
			parent, R.layout.user_recycler_view_item_user_loading
		) {
		override fun bind(value: QiitaUser?, position: Int) = Unit
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is ViewHolder) {
			holder.bind(getItem(position), position)
		}
	}
}
