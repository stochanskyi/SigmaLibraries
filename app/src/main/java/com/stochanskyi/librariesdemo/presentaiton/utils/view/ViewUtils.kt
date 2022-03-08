package com.stochanskyi.librariesdemo.presentaiton.utils.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

val ViewGroup.inflater: LayoutInflater get() = LayoutInflater.from(context)

val RecyclerView.ViewHolder.context: Context get() = itemView.context