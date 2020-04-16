package com.sorrowblue.qiichan.ui.tag

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.tag.databinding.TagFragmentMainBinding
import com.sorrowblue.qiichan.ui.view.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class TagFragment :
	DataBindingFragment<TagFragmentMainBinding>(R.layout.tag_fragment_main) {

	private val viewModel: TagViewModel by viewModel { TagViewModel.params(args.tag, args.tagId) }
	private val args: TagFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.viewModel = viewModel
		viewModel.action.observe(this) {
			when (it) {
				TagAction.ITEMS -> viewModel.tag.value?.let {
					findNavController().navigate(
						TagFragmentDirections.tagActionTagfragmentToTagitemlistfragment(it)
					)
				}
				TagAction.TREND -> toast("しばらくお待ち下さい")
			}
		}
	}
}
