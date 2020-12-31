package br.com.battlepassCalculatorValorant.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class IntroSliderAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragmentList = ArrayList<Fragment>()
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }

    fun setFragmentList(list: List<Fragment>) {
        fragmentList.clear()
        fragmentList.addAll(list)
        notifyDataSetChanged()
    }
}