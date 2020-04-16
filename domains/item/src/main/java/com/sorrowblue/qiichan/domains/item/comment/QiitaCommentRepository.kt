package com.sorrowblue.qiichan.domains.item.comment

import com.sorrowblue.qiichan.domains.item.QiitaItemId

interface QiitaCommentRepository {

	suspend fun delete(id: QiitaCommentId)

	suspend fun comment(id: QiitaCommentId): QiitaComment

	suspend fun update(id: QiitaCommentId, body: String): QiitaComment

	suspend fun comments(itemId: QiitaItemId): List<QiitaComment>

	suspend fun comment(id: QiitaCommentId, body: String): QiitaComment
}
