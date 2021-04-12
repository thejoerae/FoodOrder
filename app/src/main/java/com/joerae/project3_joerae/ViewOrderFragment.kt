package com.joerae.project3_joerae

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment

class ViewOrderFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_order_fragment, container, false)

        // Database
        val db = DataManager(view.context)

        // Table Layout Widget
        val inventoryTable = view.findViewById<TableLayout>(R.id.viewOrderLayout)

        // Remove all views (rows)
        inventoryTable.removeAllViews()

        // New table row for header
        var hdrRow = TableRow(view.context)

        // Add 5 textviews
        val tvHdr1 = TextView(view.context)
        tvHdr1.text = getString(R.string.id)
        tvHdr1.setTypeface(Typeface.DEFAULT_BOLD)

        val tvHdr2 = TextView(view.context)
        tvHdr2.text = getString(R.string.category)
        tvHdr2.setTypeface(Typeface.DEFAULT_BOLD)

        val tvHdr3 = TextView(view.context)
        tvHdr3.text = getString(R.string.item)
        tvHdr3.setTypeface(Typeface.DEFAULT_BOLD)

        val tvHdr4 = TextView(view.context)
        tvHdr4.text = getString(R.string.extras)
        tvHdr4.setTypeface(Typeface.DEFAULT_BOLD)

        val tvHdr5 = TextView(view.context)
        tvHdr5.text = getString(R.string.quantity)
        tvHdr5.setTypeface(Typeface.DEFAULT_BOLD)

        // Add textviews to table row
        hdrRow.addView(tvHdr1)
        hdrRow.addView(tvHdr2)
        hdrRow.addView(tvHdr3)
        hdrRow.addView(tvHdr4)
        hdrRow.addView(tvHdr5)

        // Add table row to table
        inventoryTable.addView(hdrRow)

        // Read data
        val data = db.readData()

        for (i in 0 until data.size) {
            // Add new table row
            val newRow = TableRow(view.context)
            newRow.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )

            // Add id textview
            val tv1 = TextView(view.context)
            tv1.text = data[i].id.toString()
            tv1.gravity = Gravity.CENTER_HORIZONTAL

            // Add category textview
            val tv2 = TextView(view.context)
            tv2.text = data[i].category.toString()

            // Add item textview
            val tv3 = TextView(view.context)
            tv3.text = data[i].item.toString()

            // Add extras textview
            val tv4 = TextView(view.context)
            tv4.text = data[i].extras.toString()

            // Add quanity textview
            val tv5 = TextView(view.context)
            tv5.text = data[i].quantity.toString()

            // Add textviews to table row
            newRow.addView(tv1)
            newRow.addView(tv2)
            newRow.addView(tv3)
            newRow.addView(tv4)
            newRow.addView(tv5)

            // Add table row to table
            inventoryTable.addView(newRow)
        }

        return view
    }
}

