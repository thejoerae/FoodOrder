package com.joerae.project3_joerae

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class AddWings : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.wings_fragment, container, false)

        // database
        val db = DataManager(view.context)

        // widgets
        val saveButton = view.findViewById<Button>(R.id.btnAddWingsToOrder)
        val wingsType = view.findViewById<RadioGroup>(R.id.rgWingsOptions)
        val blueCheese = view.findViewById<CheckBox>(R.id.chkWingsBlueCheese)
        val ranch = view.findViewById<CheckBox>(R.id.chkWingsRanch)
        val extraSauce = view.findViewById<CheckBox>(R.id.chkWingsExtraSauce)
        val quantity = view.findViewById<EditText>(R.id.etWingsQuantity)

        // save button
        saveButton.setOnClickListener {
            // variable
            var good = true

            // check to see if fields are valid
            if (quantity.text == null || quantity.text.toString() == "" || quantity.text.toString().toInt() == 0) {
                good = false
            }

            // check to see if one of the wing styles are checked. should be because we gave them a check in the XML
            val selectedWings: Int = wingsType!!.checkedRadioButtonId
            if (selectedWings == -1) {
                good = false
            }

            // if everything is good, get the values and write to the database
            if (good) {
                val newFood = Food()

                newFood.category = "Wings"

                when (selectedWings) {
                    R.id.rbWingsHot -> newFood.item = "Hot"
                    R.id.rbWingsMedium -> newFood.item = "Medium"
                    R.id.rbWingsMild -> newFood.item = "Mild"
                }

                newFood.extras = ""

                if (blueCheese.isChecked()) {
                    newFood.extras += "Blue Cheese and Celery "
                }

                // who eats ranch with wings?
                if (ranch.isChecked()) {
                    newFood.extras += "Ranch Dressing and Celery "
                }

                if (extraSauce.isChecked()) {
                    newFood.extras += "Extra Sauce"
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