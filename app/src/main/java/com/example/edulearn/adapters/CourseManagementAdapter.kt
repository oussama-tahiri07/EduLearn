package com.example.edulearn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.edulearn.databinding.ItemCourseBinding
import com.example.edulearn.models.Course

class CourseManagementAdapter(
    private val onEditClick: (Course) -> Unit,
    private val onDeleteClick: (Course) -> Unit,
    private val onAssignClick: (Course) -> Unit
) : ListAdapter<Course, CourseManagementAdapter.CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    inner class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.tvCourseTitle.text = course.title
            binding.tvCourseDescription.text = course.description

            binding.btnEdit.setOnClickListener {
                onEditClick(course)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(course)
            }

            binding.root.setOnClickListener {
                onAssignClick(course)
            }
        }
    }

    private class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}
