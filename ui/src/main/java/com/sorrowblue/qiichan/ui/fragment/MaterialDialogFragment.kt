package com.sorrowblue.qiichan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class MaterialDialogFragment : DialogFragment() {
	abstract fun onBuildDialog(builder: MaterialAlertDialogBuilder)
	override fun onCreateDialog(savedInstanceState: Bundle?) =
		MaterialAlertDialogBuilder(requireActivity()).also(::onBuildDialog).create()
}
