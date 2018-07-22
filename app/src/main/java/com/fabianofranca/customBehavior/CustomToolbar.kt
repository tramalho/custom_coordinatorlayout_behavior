package com.fabianofranca.customBehavior

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*


class CustomToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    interface OnClickItemListener {
        fun onClickFilter(headerQuickFilterItem: HeaderQuickFilterItem)
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

    fun updateQuickFilter(filters: ArrayList<CustomToolbar.HeaderQuickFilterItem>) {
        populateDropDownMenu(filters)
        val toolbarQuickFilterAdapter = filterRV.adapter as ToolbarQuickFilterAdapter
        toolbarQuickFilterAdapter.filters = filters
        toolbarQuickFilterAdapter.notifyDataSetChanged()
    }

    fun updateItem(headerQuickFilterItem: HeaderQuickFilterItem) {
        val adapter = filterRV.adapter as ToolbarQuickFilterAdapter
        adapter.updateItem(headerQuickFilterItem)
    }


    internal class ClickItem(var listener: OnClickItemListener?) : ToolbarQuickFilterAdapter.OnCheckedListener {
        override fun onClicked(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem) {
            listener?.onClickFilter(headerQuickFilterItem)
        }
    }

    data class HeaderQuickFilterItem(
            var label: String,
            var selected: Boolean = false
    )
}