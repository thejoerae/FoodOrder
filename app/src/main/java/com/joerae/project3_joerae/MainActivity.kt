package com.joerae.project3_joerae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set widgets
        val orderButton = findViewById<Button>(R.id.btnViewOrder)
        val rg = findViewById<RadioGroup>(R.id.rgOrder)

        // 	Default fragment set is View Order fragment
        var frag = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        frag = ViewOrderFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, frag)
                .commit()


        // RadioGroup/RadioButtons – setOnCheckedChangeListener
        // Replace the fragment in the fragment holder based on RadioButton selected

        rg.setOnCheckedChangeListener {
            rg,checkedID ->

            val rb = rg.findViewById<RadioButton>(checkedID)

            when (rb.id) {
                R.id.rbPizza -> {
                    var fragPizza = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                    fragPizza = AddPizza()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragPizza)
                            .commit()
                }

                R.id.rbSandwich -> {
                    var fragSandwich = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                    fragSandwich = AddSandwich()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragSandwich)
                            .commit()
                }

                R.id.rbWings -> {
                    var fragWings = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                    fragWings = AddWings()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragWings)
                            .commit()
                }
            }
        }

        // view order button
        orderButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, frag)
                    .commit()
        }

    }
}