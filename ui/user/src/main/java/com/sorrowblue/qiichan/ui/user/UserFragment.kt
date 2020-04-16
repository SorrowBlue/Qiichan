package com.sorrowblue.qiichan.ui.user

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.domains.user.UserId
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.fragment.fab
import com.sorrowblue.qiichan.ui.fragment.show
import com.sorrowblue.qiichan.ui.lifecycle.observeNotNull
import com.sorrowblue.qiichan.ui.tag.list.TagListFragmentArgs
import com.sorrowblue.qiichan.ui.user.follow.FollowListMode
import com.sorrowblue.qiichan.ui.user.follow.UserFollowListFragmentArgs
import com.sorrowblue.qiichan.ui.user.item.UserItemListFragmentArgs
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import com.sorrowblue.qiichan.ui.view.setupOpenInBrowser
import com.sorrowblue.qiichan.ui.view.snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sorrowblue.qiichan.ui.user.databinding.UserFragmentMainBinding as FragmentBinding

internal class UserFragment :
	DataBindingFragment<FragmentBinding>(R.layout.user_fragment_main, R.menu.user_menu_main) {

	private val viewModel: UserViewModel by viewModel()
	private val args: UserFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		args.user?.let(viewModel::setUser)
			?: args.author?.id?.let(viewModel::setUserId)
			?: args.userId?.let(::UserId)?.let(viewModel::setUserId)
			?: return kotlin.run { findNavController().popBackStack() }
		viewModel.uri.observe(this, this::openBrowser)
		binding.root.applyVerticalInsets()
		viewModel.user.observeNotNull(this) {
			Log.d(javaClass.simpleName, it.name ?: "@${it.userId.value}")
		}
		viewModel.action.observe(this) {
			when (it) {
				UserAction.TAG -> findNavController().navigate(
					UserFragmentDirections.actionToTagListNavigation().actionId,
					TagListFragmentArgs(viewModel.user.value!!).toBundle()
				)
				UserAction.ITEM -> findNavController().navigate(
					UserFragmentDirections.actionToUserItemListNavigation().actionId,
					UserItemListFragmentArgs(viewModel.user.value!!).toBundle()
				)
				UserAction.FOLLOWING -> findNavController().navigate(
					UserFragmentDirections.actionToUserFolloweesNavigation().actionId,
					UserFollowListFragmentArgs(
						mode = FollowListMode.FOLLOWEES,
						user = viewModel.user.value!!
					).toBundle()
				)
				UserAction.FOLLOWED -> findNavController().navigate(
					UserFragmentDirections.actionToUserFolloweesNavigation().actionId,
					UserFollowListFragmentArgs(
						mode = FollowListMode.FOLLOWERS,
						user = viewModel.user.value!!
					).toBundle()
				)
			}
		}
		var first = true
		viewModel.isFollowing.observeNotNull(this) {
			if (first) {
				first = !first
			} else {
				snackbar(if (it) "フォローしました" else "フォローを解除しました")
			}
			fab.show(if (it) R.drawable.user_ic_twotone_favorite else R.drawable.user_ic_twotone_favorite_border) {
				fab.hide()
				viewModel.follow()
			}
		}
	}

	override fun onBindMenu(menu: Menu) {
		menu.setupOpenInBrowser(requireContext(), "https://qiita.com".toUri())
	}

	fun openBrowser(url: Uri) {
		CustomTabsIntent.Builder().build().launchUrl(requireContext(), url)
	}
}
