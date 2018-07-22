package com.fabianofranca.customBehavior

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.content.res.ResourcesCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.bottom_sheet.*
import java.util.*


class BottomSheetDialogFragment : BottomSheetDialogFragment() {

    var callback: ((text:String) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater,
                                       container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.bottom_sheet, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            callback?.invoke("new ")
            dismiss()
        }
    }
}