package com.fabianofranca.customBehavior

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.bottom_sheet.*


class BottomSheetDialogFragment : BottomSheetDialogFragment() {


    override fun onCreateView(inflater: LayoutInflater,
                                       container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottom_sheet, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val color = ResourcesCompat.getColor(resources, R.color.colorPrimary, context?.theme)

        //seekBar.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        //seekBar.background.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}