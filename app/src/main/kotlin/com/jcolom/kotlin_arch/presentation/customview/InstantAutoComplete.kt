package com.jcolom.kotlin_arch.presentation.customview

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet

/*
 *   Nortia Corporation SL
 *   Copyright (C) 2018  -  All Rights Reserved
 */
class InstantAutoComplete : android.support.v7.widget.AppCompatAutoCompleteTextView {

    constructor(context: Context) : super(context) {}

    constructor(arg0: Context, arg1: AttributeSet) : super(arg0, arg1) {}

    constructor(arg0: Context, arg1: AttributeSet, arg2: Int) : super(arg0, arg1, arg2) {}

    override fun enoughToFilter(): Boolean {
        return true
    }

    override fun onFocusChanged(focused: Boolean, direction: Int,
                                previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused && adapter != null) {
            performFiltering(text, 0)
        }
    }

}