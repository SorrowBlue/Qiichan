package com.sorrowblue.qiichan.ui.auth

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import com.sorrowblue.qiichan.ui.auth.databinding.AuthFragmentMainBinding
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.lifecycle.observeData
import com.sorrowblue.qiichan.ui.tag.list.TagListFragmentArgs
import com.sorrowblue.qiichan.ui.user.follow.FollowListMode
import com.sorrowblue.qiichan.ui.user.follow.UserFollowListFragmentArgs
import com.sorrowblue.qiichan.ui.user.item.UserItemListFragmentArgs
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

internal class AuthUserFragment :
	DataBindingFragment<AuthFragmentMainBinding>(R.layout.auth_fragment_main) {

	private val viewModel: AuthUserViewModel by sharedViewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		observeData(viewModel.user) {
			if (it == null) {
				findNavController().popBackStack()
			}
		}
		viewModel.uri.observe(this, this::openBrowser)
		binding.root.applyVerticalInsets()
		viewModel.action.observe(this) {
			when (it) {
				UserAction.TAG -> findNavController().navigate(
					AuthUserFragmentDirections.actionToTagListNavigation().actionId,
					TagListFragmentArgs(viewModel.user.value!!.toUser()).toBundle()
				)
				UserAction.ITEM -> findNavController().navigate(
					AuthUserFragmentDirections.actionToUserItemListNavigation().actionId,
					UserItemListFragmentArgs(viewModel.user.value!!.toUser()).toBundle()
				)
				UserAction.FOLLOWING -> findNavController().navigate(
					AuthUserFragmentDirections.actionToUserFolloweesNavigation().actionId,
					UserFollowListFragmentArgs(
						mode = FollowListMode.FOLLOWEES,
						user = viewModel.user.value!!.toUser()
					).toBundle()
				)
				UserAction.FOLLOWED -> findNavController().navigate(
					AuthUserFragmentDirections.actionToUserFolloweesNavigation().actionId,
					UserFollowListFragmentArgs(
						mode = FollowListMode.FOLLOWERS,
						user = viewModel.user.value!!.toUser()
					).toBundle()
				)
			}
		}
	}

	private fun openBrowser(url: Uri) {
		val icon = BitmapFactory.decodeResource(
			resources,
			com.sorrowblue.qiichan.ui.R.drawable.ic_twotone_close
		)
		CustomTabsIntent.Builder()
			.setCloseButtonIcon(icon)
			.build()
			.launchUrl(requireContext(), url)
	}
}
