package com.sorrowblue.qiichan.ui.tag.item

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.fragment.toolbar
import com.sorrowblue.qiichan.ui.tag.R
import com.sorrowblue.qiichan.ui.tag.databinding.TagFragmentItemListBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class TagItemListFragment :
	DataBindingFragment<TagFragmentItemListBinding>(R.layout.tag_fragment_item_list) {

	private val args: TagItemListFragmentArgs by navArgs()
	private val viewModel: TagItemListViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		args.tag?.also {
			viewModel.setTagId(it.id)
		} ?: return kotlin.run { findNavController().popBackStack() }
		binding.viewModel = viewModel
		binding.recyclerView.applyVerticalInsets()
		toolbar.title = (args.tag?.id?.value) + "の投稿"
	}
}
