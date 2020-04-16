package com.sorrowblue.qiichan

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.sorrowblue.qiichan.databinding.FragmentSplashBinding
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class SplashFragment :
	DataBindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		viewLifecycleOwner.lifecycle.coroutineScope.launch {
			delay(2000)
			findNavController().navigate(R.id.trend_navigation, null, navOptions {
				this.popUpTo(R.id.splashFragment) { inclusive = true }
			})
		}
		requireActivity().onBackPressedDispatcher.addCallback(this, true) {}
	}

	override val fullScreen = true
}
