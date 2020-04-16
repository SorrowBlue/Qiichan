package com.sorrowblue.qiichan.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.sorrowblue.qiichan.ui.lifecycle.NonNullLiveData
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel

class UiViewModel : ViewModel(), LifecycleObserver {

	val app = NonNullLiveData(true)
	val appBarLayout = NonNullLiveData(true)

	val dist = MutableLiveData<Int>()

	@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	fun hide() {
		app.value = false
		appBarLayout.value = false
		Log.d(javaClass.simpleName, "hide")
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	fun show() {
		app.value = true
		appBarLayout.value = true
		Log.d(javaClass.simpleName, "show")
	}

	companion object {
		fun hideNavView(fragment: Fragment) {
			fragment.viewLifecycleOwner.lifecycle.addObserver(fragment.getSharedViewModel<UiViewModel>())
		}
	}
}
