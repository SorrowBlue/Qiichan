package com.sorrowblue.qiichan.ui

sealed class NetworkState {

	object LOADING_INITIAL : NetworkState()
	object LOADING_MORE : NetworkState()
	object LOADED : NetworkState()
	object FINISHED : NetworkState()
	class ERROR(val throwable: Throwable) : NetworkState()

	fun isFinished() = this is FINISHED || this is ERROR
	fun isLoading() = this is LOADING_INITIAL || this is LOADING_MORE
}
