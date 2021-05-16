package br.com.battlepassCalculatorValorant.ui.view.fragment.SplashScreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.battlepassCalculatorValorant.BuildConfig
import br.com.battlepassCalculatorValorant.databinding.FragmentSplashScreenBinding
import br.com.battlepassCalculatorValorant.ui.viewModel.activity.UIViewModel

class SplashScreenFragment : Fragment() {

    private val uiViewModel: UIViewModel by activityViewModels()
    private val navController: NavController
        get() {
            return findNavController()
        }

    private lateinit var binding: FragmentSplashScreenBinding


    override fun onStart() {
        super.onStart()
        val handle = Handler()
        handle.postDelayed(
            {
                uiViewModel.showBottomNav()
                val direction =
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment()
                navController.navigate(direction)
            },
            if (BuildConfig.DEBUG) 4000 else 1100
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}