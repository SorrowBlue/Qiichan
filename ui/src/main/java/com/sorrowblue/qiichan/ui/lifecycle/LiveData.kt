package com.sorrowblue.qiichan.ui.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe

inline fun <T> Fragment.observeData(
	data: LiveData<T>,
	crossinline onChanged: (T) -> Unit
): Observer<T> =
	data.observe(viewLifecycleOwner) {
		onChanged.invoke(it)
	}

inline fun <T : Any> LiveData<T?>.observeNotNull(
	fragment: Fragment,
	crossinline onChanged: (T) -> Unit
): Observer<T?> =
	observe(fragment.viewLifecycleOwner) {
		if (it != null) {
			onChanged.invoke(it)
		}
	}
