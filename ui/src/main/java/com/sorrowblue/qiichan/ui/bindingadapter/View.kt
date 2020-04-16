package com.sorrowblue.qiichan.ui.bindingadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.isVisibleForBinding(isVisible: Boolean?) {
	this.isVisible = isVisible ?: false
}
