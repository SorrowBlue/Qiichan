package com.sorrowblue.qiichan.ui.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.PatternDateFormat

@BindingAdapter("dateTimeTz", "format", "placeholder", requireAll = false)
fun TextView.setDateTime(
	dateTimeTz: DateTimeTz?,
	format: PatternDateFormat? = null,
	placeholder: Int? = null
) {
	text = dateTimeTz?.format(format ?: DateFormat.FORMAT_DATE)?.let { dateTime ->
		placeholder?.let { resources.getString(it, dateTime) }
	} ?: ""
}

@BindingAdapter("flexWrap", requireAll = false)
fun RecyclerView.flexMode(flexWrap: Int = FlexWrap.NOWRAP) {
	layoutManager = FlexboxLayoutManager(context).also {
		it.flexWrap = flexWrap
	}
}

@BindingAdapter("dateTimeAgo")
fun TextView.setDateTimeTzAgo(dateTimeTz: DateTimeTz?) {
	val now = DateTime.nowLocal().local
	when {
		dateTimeTz == null -> null
		now.year > dateTimeTz.year -> "${now.year - dateTimeTz.year} year ago"
		now.month > dateTimeTz.month -> "${now.month - dateTimeTz.month} month ago"
		now.dayOfMonth > dateTimeTz.dayOfMonth -> "${now.dayOfMonth - dateTimeTz.dayOfMonth} day ago"
		now.hours > dateTimeTz.hours -> "${now.hours - dateTimeTz.hours} hours ago"
		now.seconds > dateTimeTz.seconds -> "${now.seconds - dateTimeTz.seconds} seconds ago"
		else -> "update now"
	}.also(this::setText)
}
