package com.sorrowblue.qiichan.ui.auth.response

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.sorrowblue.qiichan.ui.auth.R
import com.sorrowblue.qiichan.ui.auth.databinding.AuthFragmentResponseBinding
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AuthResponseFragment :
	DataBindingFragment<AuthFragmentResponseBinding>(R.layout.auth_fragment_response) {

	private val args: AuthResponseFragmentArgs by navArgs()
	private val viewModel: AuthResponseViewModel by viewModel()
	override val fullScreen = true

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		if (args.code == null || args.state == null) {
			findNavController().popBackStack()
			return
		}
		binding.root.applyVerticalInsets()
		binding.viewModel = viewModel
		viewModel.method(args.code!!, args.state!!)
		binding.action.setOnClickListener {
			if (viewModel.isSuccess.value == true) {
				findNavController().navigate(
					"https://qiichan.sorrowblue.com/login".toUri(),
					navOptions { popUpTo(R.id.trendFragment) { inclusive = false } })
			} else {
				findNavController().popBackStack()
			}
		}
	}
}
