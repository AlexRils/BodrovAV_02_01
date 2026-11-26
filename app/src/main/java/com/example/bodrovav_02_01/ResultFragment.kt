
package com.example.bodrovav_02_01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val resultText = view.findViewById<TextView>(R.id.resultText)
        val buttonBack = view.findViewById<AppCompatButton>(R.id.buttonBack)

        val result = arguments?.getString("result")

        resultText.text = result

        buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
