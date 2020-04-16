package com.sorrowblue.qiichan.ui.user.follow

import android.os.Bundle
import android.view.View
import androidx.annotation.Keep
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.fragment.toolbar
import com.sorrowblue.qiichan.ui.user.R
import com.sorrowblue.qiichan.ui.user.databinding.UserFragmentFollowBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class UserFollowListFragment :
	DataBindingFragment<UserFragmentFollowBinding>(R.layout.user_fragment_follow) {

	private val args: UserFollowListFragmentArgs by navArgs()
	private val viewModel: UserFollowListViewModel
			by viewModel { parametersOf(args.mode, args.user) }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.recyclerView.applyVerticalInsets()
		binding.viewModel = viewModel
		viewModel.pagedList.observe(viewLifecycleOwner, viewModel.adapter::submitList)
		toolbar.title = when (args.mode) {
			FollowListMode.FOLLOWERS -> R.string.user_label_followers
			FollowListMode.FOLLOWEES -> R.string.user_label_following
		}.let { getString(it, args.user.userId.value) }
	}
}

@Keep
enum class FollowListMode {
	FOLLOWERS,
	FOLLOWEES
}
