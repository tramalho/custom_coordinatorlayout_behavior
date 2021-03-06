package com.fabianofranca.customBehavior

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fabianofranca.customBehavior.ToolbarQuickFilterAdapter.QuickFilterHolder
import kotlinx.android.synthetic.main.quick_item.view.*


class ToolbarQuickFilterAdapter(var filters: ArrayList<CustomToolbar.HeaderQuickFilterItem>)
    : RecyclerView.Adapter<QuickFilterHolder>() {

    var onCheckedListener: OnCheckedListener? = null

    interface OnCheckedListener {
        fun onClicked(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickFilterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.quick_item, parent, false)
        val holder = QuickFilterHolder(view, onCheckedListener)
        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    override fun onBindViewHolder(holder: QuickFilterHolder, position: Int) {
        holder.bind(filters[position])
    }

    fun updateItem(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem) {

        if (filters.contains(headerQuickFilterItem)) {
            val indexOf = filters.indexOf(headerQuickFilterItem)
            filters.set(indexOf, headerQuickFilterItem)
            notifyDataSetChanged()
        }
    }

    class QuickFilterHolder(itemView: View?, var onCheckedListener: OnCheckedListener?)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem) {

            configState(headerQuickFilterItem)

            configureListener(headerQuickFilterItem)
        }

        private fun configureListener(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem) {

            super.itemView.checkboxQuickFilter.setOnCheckedChangeListener { _, isChecked ->

                headerQuickFilterItem.selected = isChecked

                configState(headerQuickFilterItem)

                if (isChecked) {
                    onCheckedListener?.onClicked(headerQuickFilterItem)
                }
            }
        }

        private fun configState(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem) {
            super.itemView.checkboxQuickFilter.text = headerQuickFilterItem.label
            super.itemView.checkboxQuickFilter.isChecked = headerQuickFilterItem.selected
        }
    }
}



