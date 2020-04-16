package com.sorrowblue.qiichan.ui.bindingadapter

import androidx.annotation.ArrayRes
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.isRefreshingForBinding(isRefreshing: Boolean?) {
	this.isRefreshing = isRefreshing ?: false
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.setOnRefreshListenerForBinding(listener: SwipeRefreshLayout.OnRefreshListener?) =
	setOnRefreshListener(listener)

@BindingAdapter("colorSchemeResources")
fun SwipeRefreshLayout.colorSchemeColorsForBinding(@ArrayRes colorArrayRes: Int) {
	val array = context.resources.obtainTypedArray(colorArrayRes).use { ta ->
		IntArray(ta.length()) { ta.getColor(it, 0) }
	}
	setColorSchemeColors(*array)
}
