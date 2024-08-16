package com.fatihbaser.movietask.core

import android.content.Context
import com.fatihbaser.movietask.auxiliar.ex_activity_context.getStringResource
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    private val giveMeAppContext: Context
) {
    fun getStringResource (
        strResource: Int
    ): String {
        val context = giveMeAppContext
        return context.getStringResource(strResource)
    }
}