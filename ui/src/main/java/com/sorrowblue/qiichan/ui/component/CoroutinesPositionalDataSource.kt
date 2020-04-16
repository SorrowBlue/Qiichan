package com.sorrowblue.qiichan.ui.component

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.sorrowblue.qiichan.ui.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class CoroutinesPositionalDataSource<T : Any>(private val scope: CoroutineScope) :
	PositionalDataSource<T>() {

	abstract class Factory<T : Any, DS : CoroutinesPositionalDataSource<T>>(protected val scope: CoroutineScope) :
		DataSource.Factory<Int, T>() {
		val dataSource: MutableLiveData<DS> = MutableLiveData()
		abstract fun createDataSource(): DS
		override fun create() = createDataSource().also(dataSource::postValue)
	}

	val networkState: MutableLiveData<NetworkState> = MutableLiveData()
	val isEmpty: MutableLiveData<Boolean> = MutableLiveData()

	override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
		networkState.postValue(NetworkState.LOADING_INITIAL)
		scope.launch {
			runCatching {
				params.run { loadInitial(requestedStartPosition, requestedLoadSize, pageSize) }
			}.onSuccess {
				callback.onResult(it, 0, totalCount)
				isEmpty.postValue(it.isEmpty())
				networkState.postValue(if (totalCount <= params.requestedLoadSize) NetworkState.FINISHED else NetworkState.LOADED)
			}.onFailure {
				networkState.postValue(NetworkState.ERROR(it))
			}
		}
	}

	override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
		networkState.postValue(NetworkState.LOADING_MORE)
		scope.launch {
			runCatching {
				loadRange(params.startPosition, params.loadSize)
			}.onSuccess {
				callback.onResult(it)
				networkState.postValue(if (totalCount <= params.startPosition + params.loadSize) NetworkState.FINISHED else NetworkState.LOADED)
			}.onFailure { networkState.postValue(NetworkState.ERROR(it)) }
		}
	}

	abstract val totalCount: Int
	abstract suspend fun loadRange(startPosition: Int, loadSize: Int): List<T>
	abstract suspend fun loadInitial(startPosition: Int, loadSize: Int, pageSize: Int): List<T>
}
