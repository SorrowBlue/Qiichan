package com.sorrowblue.qiichan.ui.item.comment.editor

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.item.R
import com.sorrowblue.qiichan.ui.item.databinding.ItemViewCommentEditorBinding
import com.sorrowblue.qiichan.ui.item.databinding.ItemViewCommentPreviewBinding
import com.sorrowblue.qiichan.ui.item.setMarkdownForBinding
import com.sorrowblue.qiichan.ui.view.applyNavigationBarPaddingInsets
import com.sorrowblue.qiichan.ui.view.applySystemBarPaddingInsets
import com.sorrowblue.qiichan.ui.view.inflater
import io.noties.markwon.Markwon
import io.noties.markwon.editor.MarkwonEditor
import io.noties.markwon.editor.MarkwonEditorTextWatcher
import com.sorrowblue.qiichan.ui.item.databinding.ItemFragmentCommentEditorBinding as FragmentBinding

internal class CommentEditorFragment :
	DataBindingFragment<FragmentBinding>(R.layout.item_fragment_comment_editor) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewPager2.adapter = CommentEditorAdapter()
		binding.tab.applySystemBarPaddingInsets()
		binding.viewPager2.applyNavigationBarPaddingInsets()
		TabLayoutMediator(binding.tab, binding.viewPager2) { tab, position -> tab.text = if(position == 0) "編集" else "プレビュー" }.attach()
	}
}

internal class CommentEditorAdapter :
	RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	val md =  MutableLiveData<String>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return if (viewType == 0) {
			EditorViewHolder(parent)
		} else {
			PreviewViewHolder(parent)
		}
	}

	override fun getItemViewType(position: Int) = position

	override fun getItemCount() = 2

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (position == 0) {
			(holder as EditorViewHolder).bind()
		} else {
			(holder as PreviewViewHolder).bind()
		}

	}

	inner class EditorViewHolder private constructor(private val binding: ItemViewCommentEditorBinding) :
		RecyclerView.ViewHolder(binding.root) {

		constructor(parent: ViewGroup) :
				this(ItemViewCommentEditorBinding.inflate(parent.inflater, parent, false))

		fun bind() {
			val markwon = Markwon.create(binding.root.context)
			val editor = MarkwonEditor.create(markwon)
			binding.textInputEdit.addTextChangedListener(MarkwonEditorTextWatcher.withProcess(editor))
			binding.textInputEdit.doAfterTextChanged {
				Log.d("APPAPP", "after ${it?.toString()}")
				md.value = it?.toString() ?: ""
			}
		}
	}

	inner class PreviewViewHolder private constructor(private val binding: ItemViewCommentPreviewBinding) :
		RecyclerView.ViewHolder(binding.root) {

		constructor(parent: ViewGroup) :
				this(ItemViewCommentPreviewBinding.inflate(parent.inflater, parent, false))

		fun bind() {
			md.observeForever {
				Log.d("APPAPP", "md  $it")
				binding.text.setMarkdownForBinding(it)
			}
		}
	}

}
