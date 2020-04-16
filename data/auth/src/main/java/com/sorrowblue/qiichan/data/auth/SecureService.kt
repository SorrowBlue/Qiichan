package com.sorrowblue.qiichan.data.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

internal class SecureService(context: Context) {
	val preferences = EncryptedSharedPreferences.create(
		context.packageName,
		MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
		context,
		EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
		EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
	)
}
