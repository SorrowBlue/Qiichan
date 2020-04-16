package com.sorrowblue.qiichan.ui.item

import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import com.sorrowblue.qiichan.domains.item.QiitaItemTag
import com.sorrowblue.qiichan.ui.item.databinding.ItemViewTagBinding
import com.sorrowblue.qiichan.ui.recyclerview.DataBindAdapter

class ItemTagAdapter : DataBindAdapter<QiitaItemTag, ItemTagAdapter.ViewHolder>() {

	class ViewHolder(parent: ViewGroup) :
		DataBindAdapter.ViewHolder<QiitaItemTag, ItemViewTagBinding>(
			parent,
			R.layout.item_view_tag
		) {
		override fun bind(value: QiitaItemTag, position: Int) {
			binding.tag = value
			binding.root.setOnClickListener {
				it.findNavController()
					.navigate("https://qiichan.sorrowblue.com/tags/${value.id.value}".toUri())
			}
		}
	}

	override fun areItemsTheSame(old: QiitaItemTag, new: QiitaItemTag) = old.id == new.id

	override fun areContentsTheSame(old: QiitaItemTag, new: QiitaItemTag) = old == new

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)
}
