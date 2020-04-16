package com.sorrowblue.qiichan.data

import android.content.Context

class SecurePreferences(context: Context) {

//	val preferences = EncryptedSharedPreferences
//		.create(
//			"qiita_data",
//			MasterKeys.getOrCreate(AES256_GCM_SPEC),
//			context,
//			AES256_SIV,
//			AES256_GCM
//		)

	val preferences  = context.getSharedPreferences("qiita_data", Context.MODE_PRIVATE)
}
