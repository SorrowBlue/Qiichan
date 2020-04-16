package com.sorrowblue.qiichan.ui

import android.os.Bundle
import android.view.View
import com.sorrowblue.qiichan.ui.databinding.LoginDialogBinding
import com.sorrowblue.qiichan.ui.fragment.DataBindingDialogFragment

class LoginDialog : DataBindingDialogFragment<LoginDialogBinding>(R.layout.login_dialog) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.imageView.setOnClickListener {

		}
		binding.imageView2.setOnClickListener { }

	}
}
