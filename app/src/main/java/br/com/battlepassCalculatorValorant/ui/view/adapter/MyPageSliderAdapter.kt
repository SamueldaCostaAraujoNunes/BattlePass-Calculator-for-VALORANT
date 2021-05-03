package br.com.battlepassCalculatorValorant.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPageSliderAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val mFragmentList: ArrayList<Fragment> =  ArrayList()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }
}