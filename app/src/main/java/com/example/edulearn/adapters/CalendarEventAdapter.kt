package com.example.edulearn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.edulearn.databinding.ItemCalendarEventBinding
import com.example.edulearn.models.CalendarEvent
import java.text.SimpleDateFormat
import java.util.Locale

class CalendarEventAdapter(
    private val onItemClick: (CalendarEvent) -> Unit
) : ListAdapter<CalendarEvent, CalendarEventAdapter.EventViewHolder>(EventDiffCallback()) {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemCalendarEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    inner class EventViewHolder(
        private val binding: ItemCalendarEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(event: CalendarEvent) {
            binding.tvEventTitle.text = event.title
            binding.tvEventDescription.text = event.description
            binding.tvEventDate.text = dateFormat.format(event.date)
            binding.tvCourseName.text = event.courseName
        }
    }

    private class EventDiffCallback : DiffUtil.ItemCallback<CalendarEvent>() {
        override fun areItemsTheSame(oldItem: CalendarEvent, newItem: CalendarEvent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CalendarEvent, newItem: CalendarEvent): Boolean {
            return oldItem == newItem
        }
    }
}
