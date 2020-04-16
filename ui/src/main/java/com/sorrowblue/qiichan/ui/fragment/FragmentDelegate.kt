package com.sorrowblue.qiichan.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.sorrowblue.qiichan.ui.delegate.UiDelegate

abstract class FragmentDelegate<T> : UiDelegate<Fragment, T>() {
	override fun getLifecycleOwner(thisRef: Fragment): LifecycleOwner = thisRef.viewLifecycleOwner
}
