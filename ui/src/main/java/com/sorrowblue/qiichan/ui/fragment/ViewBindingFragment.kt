package com.sorrowblue.qiichan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class ViewBindingFragment<T : ViewBinding>(init: (LayoutInflater) -> T) : Fragment() {
	protected val binding: T by viewBinding(init)
	protected open val fabAction: FabAction? = null
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	) = binding.root

	override fun onStart() {
		super.onStart()
	}
}

typealias FabAction = Pair<Int, () -> Any?>
