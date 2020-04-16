package com.sorrowblue.qiichan.ui.user.item

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.user.R
import com.sorrowblue.qiichan.ui.user.databinding.UserFragmentItemListBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class UserItemListFragment :
	DataBindingFragment<UserFragmentItemListBinding>(R.layout.user_fragment_item_list) {

	private val viewModel: UserItemListViewModel by viewModel { parametersOf(args.user) }
	private val args: UserItemListFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		binding.recyclerView.applyVerticalInsets()
		viewModel.pagedList.observe(viewLifecycleOwner, viewModel.adapter::submitList)
	}
}
