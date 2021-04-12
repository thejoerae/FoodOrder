package com.joerae.project3_joerae

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class AddPizza : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.pizza_fragment, container, false)

        // database
        val db = DataManager(view.context)

        // widgets
        val saveButton = view.findViewById<Button>(R.id.btnAddPizzaToOrder)
        val pizzaCrust = view.findViewById<RadioGroup>(R.id.rgPizzaOptions)
        val extraCheese = view.findViewById<CheckBox>(R.id.chkExtraCheese)
        val pepperoni = view.findViewById<CheckBox>(R.id.chkPepperoni)
        val anchovies = view.findViewById<CheckBox>(R.id.chkAnchovies)
        val quantity = view.findViewById<EditText>(R.id.etPizzaQuantity)

        // save button
        saveButton.setOnClickListener {
            // variable
            var good = true

            // check to see if fields are valid
            if (quantity.text == null || quantity.text.toString() == "" || quantity.text.toString().toInt() == 0) {
                good = false
            }

            // mmm - pizza crust
            val selectedCrust: Int = pizzaCrust!!.checkedRadioButtonId
            if (selectedCrust == -1) {
                good = false
            }

            // if everything is good, get the values and write to the database
            if (good) {
                val newFood = Food()

                newFood.category = "Pizza"

                when (selectedCrust) {
                    R.id.rbPizzaCrustThin -> newFood.item = "Thin Crust"
                    R.id.rbPizzaCrustRegular -> newFood.item = "Regular Crust"
                    R.id.rbPizzaCrustSicilian -> newFood.item = "Sicilian Crust"
                }

                newFood.extras = ""

                if (extraCheese.isChecked()) {
                    newFood.extras += "Extra Cheese "
                }

                if (pepperoni.isChecked()) {
                    newFood.extras += "Pepperoni "
                }

                if (anchovies.isChecked()) {
                    newFood.extras += "Anchovies"
                }

                newFood.quantity = quantity.text.toString().toInt()

                db.insertData(newFood)
            } else {
                Toast.makeText(view.context, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}