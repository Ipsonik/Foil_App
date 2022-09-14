package com.example.foliarz

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class CalendarViewHolder(
    itemView: View,
    onItemListener: CalendarAdapter.OnItemListener,
    days: ArrayList<LocalDate>
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val onItemListener: CalendarAdapter.OnItemListener = onItemListener
    private val days: ArrayList<LocalDate> = days
    val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)
    val cellBackground: View = itemView.findViewById(R.id.cell_background)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onItemListener.onItemClick(adapterPosition, days[adapterPosition])
    }
}