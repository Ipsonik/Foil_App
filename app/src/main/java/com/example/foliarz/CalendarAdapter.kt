package com.example.foliarz

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class CalendarAdapter(
    private val days: ArrayList<LocalDate>,
    private val onItemListener: OnItemListener,
    private val selectedDate: LocalDate,
    private val dbArray: ArrayList<String>
) : RecyclerView.Adapter<CalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.1666666).toInt()
        return CalendarViewHolder(view, onItemListener, days)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = days[position]
        var backgroundchanged = false
        if (date == LocalDate.of(1930, 9, 30)) {
            holder.dayOfMonth.text = ""
            holder.dayOfMonth.background = null
            holder.dayOfMonth.foreground = null
        } else {
            holder.dayOfMonth.text = date.dayOfMonth.toString()

            if(date==selectedDate)
            {
                holder.cellBackground.setBackgroundColor(Color.DKGRAY)
                var eventTriggerFlag = false
                for(dates in dbArray){
                    if(dates == date.toString()) eventTriggerFlag=true
                }
                if(eventTriggerFlag) holder.dayOfMonth.setBackgroundResource(R.drawable.red_event_background)
                else holder.dayOfMonth.setBackgroundResource(R.drawable.edit_text_bg)
            } else {
                var eventTriggerFlag = false
                for(dates in dbArray){
                    if(dates == date.toString()) eventTriggerFlag=true
                }
                if(eventTriggerFlag) holder.dayOfMonth.setBackgroundResource(R.drawable.red_event_background)
                else holder.dayOfMonth.setBackgroundResource(R.drawable.edit_text_bg)
            }
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, date: LocalDate)
    }
}