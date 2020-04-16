package com.sorrowblue.qiichan.data.item.comment

import com.sorrowblue.qiichan.data.AndroidClient
import com.sorrowblue.qiichan.domains.item.QiitaItemId
import com.sorrowblue.qiichan.domains.item.comment.QiitaComment
import com.sorrowblue.qiichan.domains.item.comment.QiitaCommentId
import com.sorrowblue.qiichan.domains.item.comment.QiitaCommentRepository

internal class QiitaCommentRepositoryImp(private val client: AndroidClient) :
	QiitaCommentRepository {
	override suspend fun delete(id: QiitaCommentId) = client.delete<Unit>("comments/${id.value}")
	override suspend fun comment(id: QiitaCommentId): QiitaComment =
		client.get("/comments/${id.value}")

	override suspend fun comment(id: QiitaCommentId, body: String): QiitaComment =
		client.post("/items/${id.value}/comments") {
			this.body = QiitaComment.UpdateBody(body)
		}

	override suspend fun update(id: QiitaCommentId, body: String): QiitaComment =
		client.patch("/comments/${id.value}") {
			this.body = QiitaComment.UpdateBody(body)
		}

	override suspend fun comments(itemId: QiitaItemId): List<QiitaComment> =
		client.get("/items/${itemId.value}/comments")
}
