package com.sorrowblue.qiichan.ui.item

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sorrowblue.qiichan.domains.item.QiitaItemId
import com.sorrowblue.qiichan.ui.fragment.DataBindingFragment
import com.sorrowblue.qiichan.ui.fragment.toolbar
import com.sorrowblue.qiichan.ui.item.databinding.ItemFragmenMainBinding
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import com.sorrowblue.qiichan.ui.view.item
import com.sorrowblue.qiichan.ui.view.setOnClickListener
import com.sorrowblue.qiichan.ui.view.setupOpenInBrowser
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.coil.CoilImagesPlugin
import io.noties.markwon.linkify.LinkifyPlugin
import io.noties.markwon.syntax.Prism4jThemeDefault
import io.noties.markwon.syntax.SyntaxHighlightPlugin
import io.noties.prism4j.Prism4j
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sorrowblue.qiichan.ui.R as UiR

internal class ItemFragment :
	DataBindingFragment<ItemFragmenMainBinding>(
		R.layout.item_fragmen_main,
		UiR.menu.open_in_browser_menu
	) {
	private val args: ItemFragmentArgs by navArgs()
	private val viewModel: ItemViewModel by viewModel()

	override fun onBindMenu(menu: Menu) {
		menu.setupOpenInBrowser(requireContext()) { viewModel.item.value!!.url }
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.item_menu_main, menu)
		menu.item<MenuItem>(R.id.item_menu_comments) {
			it.setOnClickListener {
				findNavController().navigate(ItemFragmentDirections.actionToCommentsFragment(viewModel.item.value!!))
			}
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.vieModel = viewModel
		binding.scrollView.applyVerticalInsets()
		viewModel.item.observe(viewLifecycleOwner) {
			if (it == null) {
				findNavController().popBackStack()
			} else {
				toolbar.title = it.title
			}
		}
		binding.userIcon.setOnClickListener {
			it.findNavController()
				.navigate("https://qiichan.sorrowblue.com/users/${viewModel.item.value!!.user.userId.value}".toUri())
		}
		when {
			args.item != null -> viewModel.item.value = args.item
			args.simpleItem != null -> viewModel.setItemId(args.simpleItem!!.id)
			args.trendItem != null -> viewModel.setItemId(args.trendItem!!.id)
			args.itemId != null -> viewModel.setItemId(QiitaItemId(args.itemId!!))
			else -> findNavController().popBackStack()
		}

	}
}

@BindingAdapter("markdown")
fun TextView.setMarkdownForBinding(md: String?) {
	if (md == null) {
		text = null
		return
	}
//	findViewTreeLifecycleOwner()?.lifecycle?.coroutineScope?.launch {
		Markwon.builder(context)
			.usePlugin(CoilImagesPlugin.create(context))
			.usePlugin(HtmlPlugin.create())
			.usePlugin(LinkifyPlugin.create())
			.usePlugin(
				SyntaxHighlightPlugin.create(
					Prism4j(MyGrammarLocator()),
					Prism4jThemeDefault.create()
				)
			)
			.build()
			.setMarkdown(this@setMarkdownForBinding, md)
//	}
}
