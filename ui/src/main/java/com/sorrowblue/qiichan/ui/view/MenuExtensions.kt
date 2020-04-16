package com.sorrowblue.qiichan.ui.view

import android.content.Context
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.sorrowblue.qiichan.ui.R
import androidx.browser.R as BrowserR


inline fun <reified T : MenuItem> Menu.item(@IdRes resId: Int, noinline action: (T) -> Unit) {
	(findItem(resId) as? T)?.let(action::invoke)
}

inline fun MenuItem.setOnClickListener(crossinline action: () -> Unit) {
	setOnMenuItemClickListener {
		action.invoke()
		true
	}
}

fun Menu.setupOpenInBrowser(context: Context, uri: Uri) {
	findItem(R.id.open_in_browser)?.setOnMenuItemClickListener {
		kotlin.runCatching { CustomTabsIntent.Builder().build().launchUrl(context, uri) }
			.fold({ BrowserR.string.fallback_menu_item_open_in_browser }) { R.string.msg_no_browser_found }
			.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
		true
	}
}

fun Menu.setupOpenInBrowser(context: Context, uriCallback: () -> Uri) {
	findItem(R.id.open_in_browser)?.setOnMenuItemClickListener {
		kotlin.runCatching { CustomTabsIntent.Builder().build().launchUrl(context, uriCallback()) }
			.fold({ BrowserR.string.fallback_menu_item_open_in_browser }) { R.string.msg_no_browser_found }
			.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
		true
	}
}

@BindingAdapter("openInBrowser")
fun View.setupOpenInBrowser(uri: String) {
	setOnClickListener {
		kotlin.runCatching { CustomTabsIntent.Builder().build().launchUrl(context, uri.toUri()) }
			.fold({ BrowserR.string.fallback_menu_item_open_in_browser }) { R.string.msg_no_browser_found }
			.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
	}
}
