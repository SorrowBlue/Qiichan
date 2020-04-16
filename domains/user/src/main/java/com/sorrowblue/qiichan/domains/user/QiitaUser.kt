package com.sorrowblue.qiichan.domains.user

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.Keep
import com.sorrowblue.qiichan.domains.UriSerializer
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Parcelize
@Serializable
data class QiitaUser(
	val description: String? = null,
	@SerialName("facebook_id") private val _facebookId: String? = null,
	@SerialName("followees_count") val followeesCount: Int = -1,
	@SerialName("followers_count") val followersCount: Int = -1,
	@SerialName("github_login_name") private val _gitHubId: String? = null,
	@SerialName("id") private val _userId: String = "",
	@SerialName("items_count") val itemsCount: Int = -1,
	@SerialName("linkedin_id") private val _linkedInId: String? = null,
	val location: String? = null,
	val name: String? = null,
	val organization: String? = null,
	@SerialName("permanent_id") private val _permanentId: Int = -1,
	@SerialName("profile_image_url") @Serializable(UriSerializer::class) val profileImageUrl: Uri = Uri.EMPTY,
	@SerialName("team_only") val teamOnly: Boolean = false,
	@SerialName("twitter_screen_name") private val _twitterId: String? = null,
	@SerialName("website_url") @Serializable(UriSerializer::class) val websiteUrl: Uri? = null
) : Parcelable {

	val userId get() = UserId(_userId)
	val twitterId get() = _twitterId?.let(::TwitterId)
	val permanentId get() = PermanentId(_permanentId)
	val gitHubId get() = _gitHubId?.let(::GitHubId)
	val facebookId get() = _facebookId?.let(::FacebookId)
	val linkedInId get() = _linkedInId?.let(::LinkedInId)
}
