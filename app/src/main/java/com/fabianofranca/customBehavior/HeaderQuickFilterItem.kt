package com.fabianofranca.customBehavior

data class HeaderQuickFilterItem(
        val label : String,
        val selectedLabel : String,
        var selected : Boolean = false
)