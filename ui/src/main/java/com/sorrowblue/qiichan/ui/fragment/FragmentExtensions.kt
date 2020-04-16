package com.sorrowblue.qiichan.ui.fragment

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sorrowblue.qiichan.ui.R

val Fragment.fab: FloatingActionButton
	get() = ActivityCompat.requireViewById(requireActivity(), R.id.fab)

val Fragment.bottomAppBar: BottomAppBar
	get() = ActivityCompat.requireViewById<BottomAppBar>(requireActivity(), R.id.bottom_app_bar)

val Fragment.toolbar: Toolbar
	get() = ActivityCompat.requireViewById<Toolbar>(requireActivity(), R.id.toolbar)

inline fun FloatingActionButton.show(@DrawableRes resId: Int, crossinline action: () -> Unit) {
	setImageIcon(Icon.createWithResource(context, resId))
	setOnClickListener { action.invoke() }
	show()
}
