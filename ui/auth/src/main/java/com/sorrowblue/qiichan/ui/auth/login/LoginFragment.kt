package com.sorrowblue.qiichan.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.fragment.findNavController
import com.sorrowblue.qiichan.ui.auth.R
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import com.sorrowblue.qiichan.ui.view.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sorrowblue.qiichan.ui.auth.databinding.AuthFragmentLoginBinding as FragmentBinding

internal class LoginFragment : DataBindingFragment<FragmentBinding>(R.layout.auth_fragment_login) {

	private val viewModel: LoginViewModel by viewModel()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		if (viewModel.isLogin) {
			toast(getString(R.string.auth_msg_already_login))
			findNavController().popBackStack()
			return
		}
		binding.root.applyVerticalInsets()
		binding.viewModel = viewModel
		viewModel.uri.observe(this) {
			CustomTabsIntent.Builder().build().launchUrl(requireContext(), it)
		}
	}

	override val fullScreen = true
}

