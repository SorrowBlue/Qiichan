package com.sorrowblue.qiichan.ui.tagfeed

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.tagfeed.databinding.TagfeedFragmentMainBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import com.sorrowblue.qiichan.ui.view.setupOpenInBrowser
import com.sorrowblue.qiichan.ui.view.snackbar
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class TagFeedFragment :
	DataBindingFragment<TagfeedFragmentMainBinding>(
		R.layout.tagfeed_fragment_main,
		R.menu.trend_menu
	) {

	private val viewModel: TagFeedViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		if (get<QiitaAuthService>().authUser == null) {
			snackbar("ログインしてください", Snackbar.LENGTH_INDEFINITE) {
				setAction("ログイン") {
					findNavController().navigate("https://qiichan.sorrowblue.com/login".toUri())
				}
			}
		}
		binding.recyclerView.applyVerticalInsets()
	}

	override fun onBindMenu(menu: Menu) {
		menu.setupOpenInBrowser(requireContext(), "https://qiita.com/tag-feed".toUri())
	}
}
