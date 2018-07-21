package com.fabianofranca.customBehavior

import android.graphics.drawable.StateListDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fabianofranca.customBehavior.ToolbarQuickFilterAdapter.QuickFilterHolder
import kotlinx.android.synthetic.main.quick_item.view.*
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.DrawableContainer.DrawableContainerState


class ToolbarQuickFilterAdapter(var filters: List<HeaderQuickFilterItem>,
                                val onCheckedListener: OnCheckedListener)
    : RecyclerView.Adapter<QuickFilterHolder>() {

    private val checkeds: ArrayList<HeaderQuickFilterItem> = arrayListOf()

    interface OnCheckedListener {
        fun onClicked(headerQuickFilterItem: ArrayList<HeaderQuickFilterItem>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuickFilterHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.quick_item, parent, false)
        val holder = QuickFilterHolder(view, onCheckedListener, checkeds)
        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int {
        return filters.size
    }

    override fun onBindViewHolder(holder: QuickFilterHolder, position: Int) {
        holder.bind(filters[position])
    }

    class QuickFilterHolder(itemView: View?, var onCheckedListener: OnCheckedListener,
                            val checkeds: ArrayList<HeaderQuickFilterItem>)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(headerQuickFilterItem: HeaderQuickFilterItem) {

            configState(headerQuickFilterItem)

            configureListener(headerQuickFilterItem)
        }

        private fun configureListener(headerQuickFilterItem: HeaderQuickFilterItem) {

            super.itemView.checkboxQuickFilter.setOnCheckedChangeListener { _, isChecked ->

                headerQuickFilterItem.selected = isChecked

                configState(headerQuickFilterItem)

                onCheckedListener.onClicked(checkeds)
            }
        }

        private fun configState(headerQuickFilterItem: HeaderQuickFilterItem) {
            when (headerQuickFilterItem.selected) {
                true -> {
                    checkeds.add(headerQuickFilterItem)
                }
                else -> {
                    checkeds.remove(headerQuickFilterItem)
                }
            }

            super.itemView.checkboxQuickFilter.text = headerQuickFilterItem.label
            super.itemView.checkboxQuickFilter.isChecked = headerQuickFilterItem.selected
        }
    }
}



