package com.example.valorantpassbattle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.valorantpassbattle.R
import com.example.valorantpassbattle.model.Properties.Properties
import com.example.valorantpassbattle.ui.activity.MainActivity

class InfosFragment : Fragment() {

    private lateinit var properties: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        properties = MainActivity.Companion.properties
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infos, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        properties.historic.deleteAll()
    }
}