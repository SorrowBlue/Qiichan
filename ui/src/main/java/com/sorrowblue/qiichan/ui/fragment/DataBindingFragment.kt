package com.sorrowblue.qiichan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.sorrowblue.qiichan.ui.UiViewModel
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class DataBindingFragment<T : ViewDataBinding>(
	@LayoutRes layoutId: Int,
	@MenuRes private val menuId: Int? = null
) : Fragment() {

	protected val binding: T by dataBinding(layoutId)

	protected open fun onBindMenu(menu: Menu) {}
	protected open val fabAction: FabAction? = null
	protected open val fullScreen: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enterTransition = MaterialSharedAxis.create(MaterialSharedAxis.Z, true)
		exitTransition = MaterialSharedAxis.create(MaterialSharedAxis.Z, false)
		returnTransition = MaterialSharedAxis.create(MaterialSharedAxis.Z, false)
		menuId?.let { setHasOptionsMenu(true) }
	}

	override fun onStart() {
		super.onStart()
		fabAction?.let { fab.show(it.first) { it.second.invoke() } } ?: fab.hide()
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	) = binding.root.apply {
		getSharedViewModel<UiViewModel>().appBarLayout.value = !fullScreen
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menuId?.let {
			inflater.inflate(it, menu)
			onBindMenu(menu)
		}
	}
}

fun <T> Fragment.setResult(key: String, value: T) {
	findNavController().previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T : Any?> Fragment.resultLiveData(key: String) = ResultLiveDataDelegate<T>(key)
fun <T : Any?> Fragment.getResultOnce(key: String, callback: (T) -> Unit) =
	findNavController().currentBackStackEntry?.savedStateHandle?.let { handle ->
		handle.getLiveData<T>(key).observe(this.viewLifecycleOwner) {
			callback.invoke(it)
			handle.remove<T>(key)
		}
	}


class ResultLiveDataDelegate<T : Any?>(private val key: String) :
	ReadOnlyProperty<Fragment, MutableLiveData<T>?> {

	private var _value: MutableLiveData<T>? = null

	private fun initialize(thisRef: Fragment): MutableLiveData<T>? =
		thisRef.findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key)

	override fun getValue(thisRef: Fragment, property: KProperty<*>): MutableLiveData<T>? =
		_value ?: initialize(thisRef).also {
			_value = it
			thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
				@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
				fun onDestroy() {
					_value = null
					thisRef.viewLifecycleOwner.lifecycle.removeObserver(this)
				}
			})
		}
}
