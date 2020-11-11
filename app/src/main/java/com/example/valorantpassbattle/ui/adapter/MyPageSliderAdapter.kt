package com.example.valorantpassbattle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPageSliderAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val mFragmentList: ArrayList<Fragment> =  ArrayList()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = mFragmentList[position]
        return fragment
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    fun getItem(position: Int): Fragment? {
        return mFragmentList[position]
    }
}