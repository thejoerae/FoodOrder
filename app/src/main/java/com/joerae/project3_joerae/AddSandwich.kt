package com.joerae.project3_joerae

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class AddSandwich : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.sandwich_fragment, container, false)

        // database
        val db = DataManager(view.context)

        // widgets
        val saveButton = view.findViewById<Button>(R.id.btnAddSandwichToOrder)
        val meat = view.findViewById<RadioGroup>(R.id.rgSandwichOptions)
        val doubleMeat = view.findViewById<CheckBox>(R.id.chkDoubleMeat)
        val mayo = view.findViewById<CheckBox>(R.id.chkMayo)
        val hotPeppers = view.findViewById<CheckBox>(R.id.chkHotPeppers)
        val quantity = view.findViewById<EditText>(R.id.etSandwichQuantity)

        // save button
        saveButton.setOnClickListener {
            // variable
            var good = true

            // check to see if fields are valid
            if (quantity.text == null || quantity.text.toString() == "" || quantity.text.toString().toInt() == 0) {
                good = false
            }

            // check the meat to see if it's done
            val selectedMeat: Int = meat!!.checkedRadioButtonId
            if (selectedMeat == -1) {
                good = false
            }

            // if everything is good, get the values and write to the database
            if (good) {
                val newFood = Food()

                newFood.category = "Sandwich"

                when (selectedMeat) {
                    R.id.rbSandwichBeef -> newFood.item = "Roast Beef"
                    R.id.rbSandwichHam -> newFood.item = "Ham"
                    R.id.rbSandwichTurkey -> newFood.item = "Turkey"
                }

                newFood.extras = ""

                if (doubleMeat.isChecked()) {
                    newFood.extras += "Double Meat "
                }

                if (mayo.isChecked()) {
                    newFood.extras += "Mayo "
                }

                if (hotPeppers.isChecked()) {
                    newFood.extras += "Hot Peppers"
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