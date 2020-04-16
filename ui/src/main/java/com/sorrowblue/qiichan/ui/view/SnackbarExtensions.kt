package com.sorrowblue.qiichan.ui.view

import androidx.annotation.IntDef
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.material.snackbar.Snackbar
import com.sorrowblue.qiichan.ui.R

@IntDef(Snackbar.LENGTH_INDEFINITE, Snackbar.LENGTH_SHORT, Snackbar.LENGTH_LONG)
@Retention(AnnotationRetention.SOURCE)
annotation class SnackbarDuration

fun FragmentActivity.snackbar(
	text: CharSequence, @SnackbarDuration duration: Int = Snackbar.LENGTH_SHORT,
	body: (Snackbar.() -> Unit)? = null
) = Snackbar.make(
	ActivityCompat.requireViewById(
		this,
		R.id.coordinator
	), text, duration
)
	.also { body?.invoke(it) }.show()

fun FragmentActivity.snackbar(
	@StringRes resId: Int, @SnackbarDuration duration: Int = Snackbar.LENGTH_SHORT,
	body: (Snackbar.() -> Unit)? = null
) = Snackbar.make(
	ActivityCompat.requireViewById(
		this,
		R.id.coordinator
	), resId, duration
)
	.also { body?.invoke(it) }.show()


fun Fragment.snackbar(
	@StringRes resId: Int, @SnackbarDuration duration: Int = Snackbar.LENGTH_SHORT,
	body: (Snackbar.() -> Unit)? = null
) {
	view?.let { Snackbar.make(it, resId, duration) }?.also {
		it.setAnchorView(R.id.bottom_app_bar)
		body?.invoke(it)
	}?.show()
}

fun Fragment.snackbar(
	text: CharSequence, @SnackbarDuration duration: Int = Snackbar.LENGTH_SHORT,
	body: (Snackbar.() -> Unit)? = null
) {
	view?.let { Snackbar.make(it, text, duration) }?.also {
		it.setAnchorView(R.id.bottom_app_bar)
		it.animationMode = Snackbar.ANIMATION_MODE_SLIDE
		body?.invoke(it)
		viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
			@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
			fun onDestroy() = it.dismiss()
		})
	}?.show()
}
