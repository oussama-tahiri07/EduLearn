package com.example.edulearn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.edulearn.databinding.ItemAssignmentManagementBinding
import com.example.edulearn.models.Assignment
import java.text.SimpleDateFormat
import java.util.Locale

class AssignmentManagementAdapter(
    private val onEditClick: (Assignment) -> Unit,
    private val onDeleteClick: (Assignment) -> Unit,
    private val onViewClick: (Assignment) -> Unit
) : ListAdapter<Assignment, AssignmentManagementAdapter.AssignmentViewHolder>(AssignmentDiffCallback()) {

    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val binding = ItemAssignmentManagementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AssignmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val assignment = getItem(position)
        holder.bind(assignment)
    }

    inner class AssignmentViewHolder(
        private val binding: ItemAssignmentManagementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(assignment: Assignment) {
            binding.tvAssignmentTitle.text = assignment.title
            binding.tvAssignmentDescription.text = assignment.description
            binding.tvCourseName.text = assignment.courseName
            binding.tvDueDate.text = dateFormat.format(assignment.dueDate)

            binding.btnEdit.setOnClickListener {
                onEditClick(assignment)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(assignment)
            }

            binding.btnView.setOnClickListener {
                onViewClick(assignment)
            }
        }
    }

    private class AssignmentDiffCallback : DiffUtil.ItemCallback<Assignment>() {
        override fun areItemsTheSame(oldItem: Assignment, newItem: Assignment): Boolean {
            return oldItem.id == newItem.id && oldItem.courseName == newItem.courseName
        }

        override fun areContentsTheSame(oldItem: Assignment, newItem: Assignment): Boolean {
            return oldItem == newItem
        }
    }
}
