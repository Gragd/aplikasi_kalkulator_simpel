package com.example.kalkulatorsimpel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private var calculationList: List<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTextView: TextView = itemView.findViewById(R.id.textNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val calculation = calculationList[position]
        holder.numberTextView.text = calculation
    }

    override fun getItemCount(): Int {
        return calculationList.size
    }

    fun clearData() {
        val itemCount = calculationList.size
        (calculationList as MutableList<String>).clear()
        notifyItemRangeRemoved(0, itemCount)
    }
}