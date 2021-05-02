package br.com.battlepassCalculatorValorant.ui.view.activity.IntroTutorial

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.battlepassCalculatorValorant.R

class ThirdScreenTutorialFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third_screen_tutorial, container, false)
        val content = view.findViewById<TextView>(R.id.textView2)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            content.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
        return view
    }
}