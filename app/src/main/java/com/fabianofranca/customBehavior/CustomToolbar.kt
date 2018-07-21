package com.fabianofranca.customBehavior

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.custom_toolbar.view.*


class CustomToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr), ToolbarQuickFilterAdapter.OnCheckedListener {


    private lateinit var dropDownMenu: PopupMenu

    private var bgColor: Int
    private var textColor: Int

    init {

        inflate(context, R.layout.custom_toolbar, this)

        val t = context.theme

        val a = t.obtainStyledAttributes(attrs, com.fabianofranca.customBehavior.R.styleable.CustomToolbar, 0, 0)

        bgColor = a.getColor(R.styleable.CustomToolbar_menuBGColor, getDefaultColor(android.R.color.white))
        textColor = a.getColor(R.styleable.CustomToolbar_menuTextColor, getDefaultColor(R.color.colorPrimary))

        a.recycle()

        configRecyclerView(context)

        configDropDownMenu()
    }

    private fun getDefaultColor(color: Int): Int {
        return ResourcesCompat.getColor(resources, color, context.theme)
    }


    private fun configDropDownMenu() {

        dropDownMenu = PopupMenu(context, dropDownMenuButton)

        dropDownMenuButton.setOnClickListener {
            dropDownMenu.show()
        }

        populateDropDownMenu(arrayListOf())
    }

    private fun populateDropDownMenu(itens: List<HeaderQuickFilterItem>) {
        val menu = dropDownMenu.menu

        itens.forEach {
            menu.add(it.label)
        }
    }

    private fun configRecyclerView(context: Context) {
        filterRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        filterRV.adapter = ToolbarQuickFilterAdapter(arrayListOf(), this)
    }

    fun updateQuickFilter(filters: List<HeaderQuickFilterItem>) {
        populateDropDownMenu(filters)
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