package br.com.samuelnunes.valorantpassbattle.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentSliderAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

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
