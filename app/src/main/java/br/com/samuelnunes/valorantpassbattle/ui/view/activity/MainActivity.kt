package br.com.samuelnunes.valorantpassbattle.ui.view.activity

import android.annotation.SuppressLint
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
import br.com.samuelnunes.valorantpassbattle.extensions.getColorFromAttr
import br.com.samuelnunes.valorantpassbattle.extensions.setFontFamily
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity.UIViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
//        getToken()
    }

    private fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.w(task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Timber.d(token)
        })
    }

    @SuppressLint("ShowToast")
    override fun getSnackInstance(string: String, duration: Int): Snackbar {
        return make(binding.root, string, duration)
            .setAnchorView(binding.fab)
            .setBackgroundTint(getColorFromAttr(R.attr.colorSecondary))
            .setTextColor(getColorFromAttr(R.attr.colorOnPrimary))
            .setFontFamily(R.font.valorant_font_family)
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