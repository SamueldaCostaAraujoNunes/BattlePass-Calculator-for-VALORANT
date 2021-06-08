package br.com.samuelnunes.valorantpassbattle.ui.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.samuelnunes.valorantpassbattle.NavGraphDirections
import br.com.samuelnunes.valorantpassbattle.R
import br.com.samuelnunes.valorantpassbattle.databinding.ActivityMainBinding
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity.UIViewModel
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

    override fun onStart() {
        super.onStart()
        mostrarDadosDaNotificacao()
    }

    private fun mostrarDadosDaNotificacao() {
        if (intent.hasExtra("title") && intent.hasExtra("content")) {
            val title = intent.getStringExtra("title")
            val content = intent.getStringExtra("content")
            if (title != null && content != null) {
                navController.navigate(NavGraphDirections.actionGlobalDialogNews(title, content))
            }
        }
    }

    private fun setupObservers() {
        uiViewModel.onHideBottomNav.observe(this, {
            binding.bottomNav.transform(binding.fab, it)
        })
    }

    private fun createListeners() {
        binding.fab.setOnClickListener {
            navController.navigate(NavGraphDirections.actionGlobalDialogInput())
        }
    }
}