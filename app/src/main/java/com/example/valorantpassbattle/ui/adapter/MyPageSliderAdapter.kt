package com.example.valorantpassbattle.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.valorantpassbattle.ui.fragment.Principal.ChartFragment
import com.example.valorantpassbattle.ui.fragment.Principal.TierCurrentFragment
import com.example.valorantpassbattle.ui.fragment.Principal.TiersFragment
import org.jetbrains.annotations.Nullable

class MyPageSliderAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val mFragmentList: ArrayList<Fragment> =  ArrayList()
    private val mFragmentTitleList: ArrayList<String> = ArrayList()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = mFragmentList[position]
        return fragment
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    @Nullable
    fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun getItem(position: Int): Fragment? {
        return mFragmentList[position]
    }
}