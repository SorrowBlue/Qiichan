package com.sorrowblue.qiichan.ui.settings

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sorrowblue.qiichan.domains.auth.QiitaAuthService
import com.sorrowblue.qiichan.ui.view.applyVerticalInsets
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

internal class SettingsFragment : PreferenceFragmentCompat() {

	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		setPreferencesFromResource(R.xml.settings_preferences, rootKey)
	}

	override fun onBindPreferences() {
		findPreference<ListPreference>(getString(R.string.settings_key_dark_mode))?.setOnPreferenceChangeListener { _, newValue ->
			Settings.applyDarkMode(
				requireContext(),
				newValue as? String ?: return@setOnPreferenceChangeListener false
			)
			true
		}
		findPreference<Preference>("login")?.isVisible = get<QiitaAuthService>().authUser == null
		findPreference<Preference>("login")?.setOnPreferenceClickListener {
			findNavController().navigate("https://qiichan.sorrowblue.com/login".toUri(), navOptions {
				popUpTo = R.id.trendFragment
			})
			true
		}
		findPreference<Preference>("logout")?.isVisible = get<QiitaAuthService>().authUser != null
		findPreference<Preference>("logout")?.setOnPreferenceClickListener {
			viewLifecycleOwner.lifecycle.coroutineScope.launch {
				get<QiitaAuthService>().logout()
				findNavController().popBackStack(R.id.trendFragment, false)
			}
			true
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.applyVerticalInsets()
	}
}
