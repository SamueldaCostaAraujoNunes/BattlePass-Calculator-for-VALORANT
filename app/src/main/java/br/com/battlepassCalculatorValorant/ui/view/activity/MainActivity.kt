package br.com.battlepassCalculatorValorant.ui.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.battlepassCalculatorValorant.NavGraphDirections
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.databinding.ActivityMainBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.activity.UIViewModel
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AdmobInterstitialActivity() {
    private lateinit var binding: ActivityMainBinding
    private val uiViewModel by viewModels<UIViewModel>()

    private val navController
        get() = findNavController(R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.blue_100))
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.root.doOnLayout {
            NavigationUI.setupWithNavController(binding.bottomNav, navController)
        }
        setupObservers()
        createListeners()
    }

    private fun setupObservers() {
        uiViewModel.onHideBottomNav.observe(this, { active ->
            val params = binding.bottomNav.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior as HideBottomViewOnScrollBehavior

            with(binding.bottomNav) {
                if (active) {
                    doOnLayout { view -> behavior.slideUp(view) }
                } else {
                    doOnLayout { view -> behavior.slideDown(view) }
                }
                transform(binding.fab, active)
            }
        })
    }

    private fun createListeners() {
        binding.fab.setOnClickListener {
            navController.navigate(NavGraphDirections.actionGlobalDialogInput())
        }
    }
}