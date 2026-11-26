package com.example.bodrovav_02_01

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class FierstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view = inflater.inflate(R.layout.fragment_fierst, container, false)

      val edittext1 = view.findViewById<EditText>(R.id.editText)
      val edittext2 = view.findViewById<EditText>(R.id.editText2)
      val button1 = view.findViewById<AppCompatButton>(R.id.button1)

      val login = "ects"
      val password = "ects2026"

      val shared = view.context.getSharedPreferences("Date", Context.MODE_PRIVATE )

      val edit = shared.edit()

      button1.setOnClickListener(){

          val edittexttostring = edittext1.text.toString()
          val edittexttostring2 = edittext2.text.toString()

          if (edittexttostring != "" && edittexttostring2 != ""){

              val sharedchek1 = shared.getString("log", "")

              val sharedchek2 = shared.getString("pas", "")

              if(sharedchek1 == "" && sharedchek2 == ""){

                  edit.putString("log", edittexttostring)
                  edit.putString("pas", edittexttostring2)
                  edit.apply()

                  Toast.makeText(view.context, "Вы зарегестрированы!", Toast.LENGTH_SHORT).show()


                  parentFragmentManager.beginTransaction()
                      .replace(R.id.fragmentmanager, SecondFragment())
                      .commit()

              }
              else {

                  if( edittexttostring == login &&  edittexttostring2 == password){


                      Toast.makeText(view.context, "Добро пожаловать", Toast.LENGTH_SHORT).show()


                      parentFragmentManager.beginTransaction()
                          .replace(R.id.fragmentmanager, SecondFragment())
                          .commit()

                  }
                  else {

                      Toast.makeText(view.context, "Не правильный логин или пароль", Toast.LENGTH_SHORT).show()


                  }

              }


          }
          else {

              Toast.makeText(view.context, "Заполните поля", Toast.LENGTH_SHORT).show()

          }


      }



       return view
    }


}