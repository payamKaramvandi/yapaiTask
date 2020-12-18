package com.example.yapai.presentation.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.yapai.presentation.adapter.RecyclerItemClickListener

fun RecyclerView.affectOnItemClicks(
    onClick: ((position: Int, view: View) -> Unit)? = null,
    onLongClick: ((position: Int, view: View) -> Unit)? = null
) {
    this.addOnChildAttachStateChangeListener(
        RecyclerItemClickListener(
            this,
            onClick,
            onLongClick
        )
    )
}