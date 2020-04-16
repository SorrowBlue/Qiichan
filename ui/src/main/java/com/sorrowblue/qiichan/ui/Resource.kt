package com.sorrowblue.qiichan.ui

import android.content.res.ColorStateList
import android.widget.ProgressBar
import androidx.annotation.AttrRes
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter

@BindingAdapter("progressTintByAttr")
fun ProgressBar.progressTintByAttr(@AttrRes attrRes: Int) {
	progressTintList = ColorStateList.valueOf(
		context.obtainStyledAttributes(intArrayOf(attrRes)).use { it.getColorOrThrow(0) })
}
