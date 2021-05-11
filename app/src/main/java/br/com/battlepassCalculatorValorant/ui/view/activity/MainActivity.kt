package br.com.battlepassCalculatorValorant.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.ui.view.dialog.DialogInput
import br.com.battlepassCalculatorValorant.ui.view.fragment.BottomNavigation.ChartsFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.BottomNavigation.HomeFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.BottomNavigation.InfosFragment
import br.com.battlepassCalculatorValorant.ui.view.fragment.BottomNavigation.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment(R.id.fragmentPrincipal, HomeFragment())
        createListeners()
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.item_home
    }

    private fun createFragment(layout: Int, fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(layout, fragment).commit()
    }

    private fun createListeners() {
        createNavigationItemSelectedListener()
        fab.setOnClickListener {
            DialogInput().show(supportFragmentManager, "Teste")
        }
    }

    private fun createNavigationItemSelectedListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val previousItem = bottomNavigationView.selectedItemId
            val nextItem = item.itemId
            if (previousItem != nextItem) {
                val fragment: androidx.fragment.app.Fragment =
                    when (nextItem) {
                        R.id.item_home -> HomeFragment()
                        R.id.item_timeline -> ChartsFragment()
                        R.id.item_timer -> InfosFragment()
                        R.id.item_apps -> SettingsFragment()
                        else -> HomeFragment()
                    }
                val config = nextItem != R.id.item_apps
                bottomNavigationView.transform(fab, config)
                createFragment(R.id.fragmentPrincipal, fragment)
//                launcherAdMob()
            }
            true
        }
    }
}