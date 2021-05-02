package br.com.battlepassCalculatorValorant.ui.view.activity.IntroTutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import br.com.battlepassCalculatorValorant.R
import br.com.battlepassCalculatorValorant.database.SharedPreferences.FirstInputSharedPreferences
import br.com.battlepassCalculatorValorant.ui.view.activity.MainActivity
import br.com.battlepassCalculatorValorant.ui.view.adapter.IntroSliderAdapter
import kotlinx.android.synthetic.main.activity_intro_tutorial.*

class IntroTutorialActivity : AppCompatActivity() {
    private val fragmentList = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_intro_tutorial)
        val adapter = IntroSliderAdapter(this)
        pager.adapter = adapter
        fragmentList.addAll(
            listOf(
                FirstScreenTutorialFragment(),
                SecondScreenTutorialFragment(),
                ThirdScreenTutorialFragment(),
                InputScreenTutorialFragment()
            )
        )
        adapter.setFragmentList(fragmentList)
        dots_indicator.setViewPager2(pager)
        dots_indicator.dotsClickable = false
        registerListeners()
    }

    private fun registerListeners() {
        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position < fragmentList.lastIndex) {
                    tvNext.visibility = View.VISIBLE
                    tvNext.text = getString(R.string.proximo)
                } else {
                    tvNext.visibility = View.GONE
                }
            }
        })
        tvSkip.setOnClickListener {
            nextActivity()
        }
        tvNext.setOnClickListener {
            val position = pager.currentItem
            if (position < fragmentList.lastIndex) {
                pager.currentItem = position + 1
            }
        }
    }

    fun nextActivity() {
        FirstInputSharedPreferences(this).firstInputExists = true
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}