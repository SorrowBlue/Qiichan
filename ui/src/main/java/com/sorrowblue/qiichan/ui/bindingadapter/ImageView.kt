package com.sorrowblue.qiichan.ui.bindingadapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import coil.api.load
import coil.transform.Transformation
import com.sorrowblue.qiichan.ui.R

@BindingAdapter("coil_uri", "coil_transformation", requireAll = false)
fun ImageView.setPhotoUri(uri: Uri? = null, transformation: Transformation? = null) {
	load(uri) {
		allowHardware(false)
		lifecycle(findViewTreeLifecycleOwner())
		error(R.drawable.ic_twotone_broken_image)
		transformation?.also { transformations(it) }
	}
}
