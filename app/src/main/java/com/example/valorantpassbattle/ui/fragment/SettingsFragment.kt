package com.example.valorantpassbattle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        clear_historic.setOnClickListener { MainActivity.historic.clear() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MainActivity.historic.deleteAll()
    }
}