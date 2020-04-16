package com.sorrowblue.qiichan.ui.item.comment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.item.R
import com.sorrowblue.qiichan.ui.item.databinding.ItemFragmentCommentsBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import com.sorrowblue.qiichan.ui.R as UiR

internal class CommentsFragment :
	DataBindingFragment<ItemFragmentCommentsBinding>(R.layout.item_fragment_comments) {

	private val args: CommentsFragmentArgs by navArgs()
	private val viewModel: CommentsViewModel by viewModel { parametersOf(args.item) }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.root.applyVerticalInsets()
		binding.viewModel = viewModel
	}

	override val fabAction = UiR.drawable.ic_twotone_add_comment to {
		findNavController().navigate(CommentsFragmentDirections.actionToCommentEditorFragment())
	}
}
