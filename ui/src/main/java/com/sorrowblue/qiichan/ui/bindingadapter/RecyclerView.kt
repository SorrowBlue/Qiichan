package com.sorrowblue.qiichan.ui.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.sorrowblue.qiichan.ui.recyclerview.GridDividerDecoration

@BindingAdapter("itemDecoration")
fun RecyclerView.setDividerDecoration(orientation: Int?) = when {
	orientation == GridDividerDecoration.GRID -> addItemDecoration(GridDividerDecoration(context))
	orientation != null -> addItemDecoration(DividerItemDecoration(context, orientation))
	else -> repeat(itemDecorationCount, this::removeItemDecorationAt)
}
