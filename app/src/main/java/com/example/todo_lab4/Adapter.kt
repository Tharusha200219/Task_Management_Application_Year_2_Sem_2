package com.example.todo_lab4

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // ViewHolder class to hold references to views
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val priority: TextView = itemView.findViewById(R.id.priority)
        val date: TextView = itemView.findViewById(R.id.date)
        val layout: View = itemView.findViewById(R.id.mylayout)
    }

    // Create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    // Bind data to views
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Set background color based on priority
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#3FC060"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#3FC060"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#3FC060"))
        }

        // Set title, priority, and date
        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.date.text = SimpleDateFormat("MMM dd, yyyy").format(data[position].date)

        // Handle item click to open UpdateCard activity
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    // Return item count
    override fun getItemCount(): Int {
        return data.size
    }
}
