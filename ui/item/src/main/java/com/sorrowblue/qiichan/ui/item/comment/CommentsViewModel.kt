package com.sorrowblue.qiichan.ui.item.comment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.domains.item.QiitaItem
import com.sorrowblue.qiichan.domains.item.comment.QiitaCommentRepository
import kotlinx.coroutines.launch

internal class CommentsViewModel(
	item: QiitaItem,
	val adapter: CommentsAdapter,
	private val repo: QiitaCommentRepository,
	private val userService: QiitaAuthService
) : ViewModel() {

	init {
		adapter.userId = userService.authUser?.toUser()?.userId
		viewModelScope.launch {
			kotlin.runCatching {
				repo.comments(item.id)
			}.onSuccess { adapter.currentList = it }
				.onFailure {
					Log.d(
						CommentsViewModel::class.java.simpleName,
						"comments ${it.message}"
					)
				}
		}
	}
}
