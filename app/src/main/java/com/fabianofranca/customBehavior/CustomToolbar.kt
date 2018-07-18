package com.fabianofranca.customBehavior

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class CustomToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr), ToolbarQuickFilterAdapter.OnCheckedListener {

    init {

        //retrieve attribs

        inflate(context, R.layout.custom_toolbar, this)

        configRecyclerView(context)
    }

    private fun configRecyclerView(context: Context) {
        filterRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        filterRV.adapter = ToolbarQuickFilterAdapter(arrayListOf(), this)
    }

    fun updateQuickFilter(filters : List<HeaderQuickFilterItem>) {
        val toolbarQuickFilterAdapter = filterRV.adapter as ToolbarQuickFilterAdapter
        toolbarQuickFilterAdapter.filters = filters
        toolbarQuickFilterAdapter.notifyDataSetChanged()
    }

    override fun onClicked(headerQuickFilterItem: ArrayList<HeaderQuickFilterItem>) {

        val builder = StringBuilder()

        headerQuickFilterItem.forEach {
            builder.append(it.label).append("\n")
        }

        Toast.makeText(context, builder.toString(), Toast.LENGTH_SHORT).show()
    }
}