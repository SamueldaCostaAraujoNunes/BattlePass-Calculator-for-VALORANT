package br.com.samuelnunes.valorantpassbattle.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom.FragmentWithTitle

class FragmentSliderAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val mFragmentList: ArrayList<FragmentWithTitle> = ArrayList()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): FragmentWithTitle {
        return mFragmentList[position]
    }

    fun getItem(position: Int): FragmentWithTitle {
        return mFragmentList[position]
    }

    fun addFragments(fragments: List<FragmentWithTitle>) {
        mFragmentList.addAll(fragments)
    }

    fun setFragmentList(list: List<FragmentWithTitle>) {
        mFragmentList.clear()
        mFragmentList.addAll(list)
        notifyDataSetChanged()
    }
}
