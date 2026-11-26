
package com.example.bodrovav_02_01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val edittext5 = view.findViewById<EditText>(R.id.editText5)
        val edittext4 = view.findViewById<EditText>(R.id.editText4)

        val imageview = view.findViewById<ImageView>(R.id.imageView2)
        val button = view.findViewById<AppCompatButton>(R.id.buttonotvet)
        val spinner = view.findViewById<Spinner>(R.id.spinner)

        val figure = arrayOf("Круг", "Треугольник")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            figure
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spinner.adapter = adapter

        var idfigure = 0

        spinner.setOnItemSelectedListener(
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    idfigure = position

                    if (position == 0) {

                        imageview.setImageResource(R.drawable.circle)

                        edittext5.hint = "Введите P"
                        edittext4.visibility = View.GONE

                    }

                    if (position == 1) {

                        imageview.setImageResource(R.drawable.triangle)

                        edittext5.hint = "Введите a"
                        edittext4.hint = "Введите b"

                        edittext4.visibility = View.VISIBLE

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        )

        button.setOnClickListener {

            if (idfigure == 0) {

                val p = edittext5.text.toString().toDoubleOrNull()

                if (p == null || p <= 0) {

                    AlertDialog.Builder(requireContext())
                        .setMessage("Введите правильное значение P")
                        .setPositiveButton("ОК", null)
                        .show()

                } else {

                    val r = p / (2 * Math.PI)

                    val bundle = Bundle()
                    bundle.putString(
                        "result",
                        "Радиус круга R = ${"%.2f".format(r)}"
                    )

                    val fragment = ResultFragment()
                    fragment.arguments = bundle

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentmanager, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }

            if (idfigure == 1) {

                val a = edittext5.text.toString().toDoubleOrNull()
                val b = edittext4.text.toString().toDoubleOrNull()

                if (a == null || b == null || a <= 0 || b <= 0) {

                    AlertDialog.Builder(requireContext())
                        .setMessage("Введите правильные значения a и b")
                        .setPositiveButton("ОК", null)
                        .show()

                } else {

                    val p = 2 * a + b

                    val bundle = Bundle()
                    bundle.putString(
                        "result",
                        "Периметр треугольника P = ${"%.2f".format(p)}"
                    )

                    val fragment = ResultFragment()
                    fragment.arguments = bundle

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragmentmanager, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        return view
    }
}
