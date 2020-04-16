package com.sorrowblue.qiichan.ui.trend

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.net.toUri
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.fragment.toolbar
import com.sorrowblue.qiichan.ui.trend.databinding.TrendFragmentMainBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import com.sorrowblue.qiichan.ui.view.setupOpenInBrowser
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class TrendFragment :
	DataBindingFragment<TrendFragmentMainBinding>(R.layout.trend_fragment_main, R.menu.trend_menu) {

	private val viewModel: TrendViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		binding.recyclerView.applyVerticalInsets()
	}

	override fun onBindMenu(menu: Menu) {
		menu.setupOpenInBrowser(requireContext(), "https://qiita.com".toUri())
	}

	override fun onStart() {
		super.onStart()
		toolbar.subtitle = viewModel.trendDateTime.format("yyyy-MM-dd HH:mm")
	}

	override fun onStop() {
		super.onStop()
		toolbar.subtitle = null
	}
}
