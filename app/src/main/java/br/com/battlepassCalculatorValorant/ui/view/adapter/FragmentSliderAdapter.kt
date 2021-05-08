package br.com.battlepassCalculatorValorant.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentSliderAdapter : FragmentStateAdapter {
    constructor(fragment: Fragment) : super(fragment)
    constructor(fa: FragmentActivity) : super(fa)

    private val mFragmentList: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragments(fragments: List<Fragment>) {
        mFragmentList.addAll(fragments)
    }

    fun setFragmentList(list: List<Fragment>) {
        mFragmentList.clear()
        mFragmentList.addAll(list)
        notifyDataSetChanged()
    }
}
