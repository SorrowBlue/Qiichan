package com.sorrowblue.qiichan.domains.auth

import android.net.Uri
import com.sorrowblue.qiichan.domains.UriSerializer
import com.sorrowblue.qiichan.domains.user.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QiitaAuthUser private constructor(
	val description: String?,
	@SerialName("facebook_id") private val _facebookId: String?,
	@SerialName("followees_count") val followeesCount: Int,
	@SerialName("followers_count") val followersCount: Int,
	@SerialName("github_login_name") private val _gitHubId: String?,
	@SerialName("id") private val _userId: String,
	@SerialName("items_count") val itemsCount: Int,
	@SerialName("linkedin_id") private val _linkedInId: String?,
	val location: String?,
	val name: String?,
	val organization: String?,
	@SerialName("permanent_id") private val _permanentId: Int,
	@SerialName("profile_image_url") @Serializable(UriSerializer::class) val profileImageUrl: Uri,
	@SerialName("team_only") val teamOnly: Boolean = false,
	@SerialName("twitter_screen_name") private val _twitterId: String?,
	@SerialName("website_url") @Serializable(UriSerializer::class) val websiteUrl: Uri?,
	@SerialName("image_monthly_upload_limit") val imageMonthlyUploadLimit: Int,
	@SerialName("image_monthly_upload_remaining") val imageMonthlyUploadRemaining: Int
) {

	val userId get() = UserId(_userId)
	val twitterId get() = _twitterId?.let(::TwitterId)
	val permanentId get() = PermanentId(_permanentId)
	val gitHubId get() = _gitHubId?.let(::GitHubId)
	val facebookId get() = _facebookId?.let(::FacebookId)
	val linkedInId get() = _linkedInId?.let(::LinkedInId)

	val imageMonthlyUploadLimitMB = String.format("%.2f", imageMonthlyUploadLimit / 1048576f)
	val imageMonthlyUploadRemainingMB =
		String.format("%.2f", imageMonthlyUploadRemaining / 1048576f)

	fun toUser() = QiitaUser(
		description = description,
		_facebookId = facebookId?.value,
		followeesCount = followeesCount,
		followersCount = followersCount,
		_gitHubId = gitHubId?.value,
		_userId = userId.value,
		itemsCount = itemsCount,
		_linkedInId = linkedInId?.value,
		location = location,
		name = name,
		organization = organization,
		_permanentId = permanentId.value,
		profileImageUrl = profileImageUrl,
		teamOnly = teamOnly,
		_twitterId = twitterId?.value,
		websiteUrl = websiteUrl
	)
}
