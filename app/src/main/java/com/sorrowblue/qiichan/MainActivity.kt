package com.sorrowblue.qiichan

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AppLaunchChecker
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.*
import com.sorrowblue.qiichan.databinding.ActivityMainBinding
import com.sorrowblue.qiichan.ui.UiViewModel
import com.sorrowblue.qiichan.ui.activity.dataBinding
import com.sorrowblue.qiichan.ui.view.applyNavigationBarPaddingInsets
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sorrowblue.qiichan.ui.auth.R as AuthR
import com.sorrowblue.qiichan.ui.tagfeed.R as TagfeedR
import com.sorrowblue.qiichan.ui.trend.R as TrendR

private const val FULL_SCREEN =
	View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

class MainActivity : AppCompatActivity() {

	private val appBarConfiguration
		get() =
			AppBarConfiguration(
				setOf(
					TrendR.id.trendFragment,
					TagfeedR.id.tagFeedFragment,
					AuthR.id.authUserFragment
				), binding.drawerLayout
			)
	private val binding: ActivityMainBinding by dataBinding(R.layout.activity_main)
	private val navController: NavController by navController()
	private val uiViewModel: UiViewModel by viewModel()
	private val viewModel: MainViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setSupportActionBar(binding.appBarMain.toolbar)
		viewModel.navigation.observe(this, navController::navigate)
		binding.uiViewModel = uiViewModel
		binding.root.systemUiVisibility = FULL_SCREEN
		binding.navView.applyNavigationBarPaddingInsets()
		binding.navView.setupWithNavController(navController)
		setupActionBarWithNavController(navController, appBarConfiguration)
		binding.root.doOnLayout {
			binding.navView.updateLayoutParams<DrawerLayout.LayoutParams> { width = it.width }
		}
		navController.addOnDestinationChangedListener { _, _, _ ->
			binding.appBarMain.bottomAppBar.performShow()
		}
		AppLaunchChecker.onActivityCreate(this)
	}

	override fun onSupportNavigateUp(): Boolean =
		navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

	override fun onOptionsItemSelected(item: MenuItem): Boolean =
		super.onOptionsItemSelected(item) || item.onNavDestinationSelected(navController)
}
