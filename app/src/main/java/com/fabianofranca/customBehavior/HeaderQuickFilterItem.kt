package com.fabianofranca.customBehavior

data class HeaderQuickFilterItem(
        val label : String,
        var selected : Boolean = false,
        val type : FilterType
)