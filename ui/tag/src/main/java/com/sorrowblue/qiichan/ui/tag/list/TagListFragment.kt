package com.sorrowblue.qiichan.ui.tag.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.fragment.toolbar
import com.sorrowblue.qiichan.ui.tag.R
import com.sorrowblue.qiichan.ui.tag.databinding.TagFragmentListBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class TagListFragment :
	DataBindingFragment<TagFragmentListBinding>(R.layout.tag_fragment_list) {

	private val viewModel: TagListViewModel by viewModel { parametersOf(args.user) }
	private val args: TagListFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		binding.recyclerView.applyVerticalInsets()
		(args.user.name ?: args.user.userId.value).let {
			toolbar.title = "${it}がフォロー中のタグ"
		}
	}
}
