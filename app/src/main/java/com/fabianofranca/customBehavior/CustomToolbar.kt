package com.fabianofranca.customBehavior

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.custom_toolbar.view.*


class CustomToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    interface OnClickItemListener {
        fun onClickFilter(headerQuickFilterItem: ArrayList<HeaderQuickFilterItem>)
        fun onClickMenu(headerQuickFilterItem: HeaderQuickFilterItem)
    }

    private lateinit var dropDownMenu: PopupMenu

    var listener: OnClickItemListener? = null
    set(value) {
        filterRV.adapter?.let {
            it as ToolbarQuickFilterAdapter
           it.onCheckedListener = ClickItem(value)
        }
    }

    init {

        inflate(context, R.layout.custom_toolbar, this)

        configRecyclerView(context)

        configDropDownMenu()
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

        filterRV.adapter = ToolbarQuickFilterAdapter(arrayListOf())
    }

    fun updateQuickFilter(filters: List<HeaderQuickFilterItem>) {
        populateDropDownMenu(filters)
        val toolbarQuickFilterAdapter = filterRV.adapter as ToolbarQuickFilterAdapter
        toolbarQuickFilterAdapter.filters = filters
        toolbarQuickFilterAdapter.notifyDataSetChanged()
    }


    internal class ClickItem(var listener: OnClickItemListener?) : ToolbarQuickFilterAdapter.OnCheckedListener {
        override fun onClicked(headerQuickFilterItem: ArrayList<HeaderQuickFilterItem>) {
            listener?.onClickFilter(headerQuickFilterItem)
        }
    }
}