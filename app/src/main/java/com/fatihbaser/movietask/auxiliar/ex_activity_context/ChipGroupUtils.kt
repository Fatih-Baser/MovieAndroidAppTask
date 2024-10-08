package com.fatihbaser.movietask.auxiliar.ex_activity_context

import android.content.Context
import android.view.View
import com.fatihbaser.movietask.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.addChip(context: Context, label: String): Chip {
    return Chip(context).apply {
        id = View.generateViewId()
        text = label
        isClickable = true
        isCheckable = false
        setChipSpacingHorizontalResource(R.dimen.dimen_16)
        isCheckedIconVisible = false
        isFocusable = true
        addView(this)
    }
}

