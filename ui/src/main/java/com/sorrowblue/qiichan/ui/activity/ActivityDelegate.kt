package com.sorrowblue.qiichan.ui.activity

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.sorrowblue.qiichan.ui.delegate.UiDelegate

abstract class ActivityDelegate<T> : UiDelegate<FragmentActivity, T>() {
	override fun getLifecycleOwner(thisRef: FragmentActivity): LifecycleOwner = thisRef
}
