package com.example.edulearn.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edulearn.adapters.AssignmentManagementAdapter
import com.example.edulearn.databinding.FragmentManageAssignmentsBinding
import com.example.edulearn.utils.TokenManager
import com.example.edulearn.viewmodels.AdminViewModel
import com.example.edulearn.viewmodels.AssessmentViewModel
import com.example.edulearn.viewmodels.ViewModelFactory

class ManageAssignmentsFragment : Fragment() {
    
    private var _binding: FragmentManageAssignmentsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var assessmentViewModel: AssessmentViewModel
    private lateinit var tokenManager: TokenManager
    private lateinit var assignmentAdapter: AssignmentManagementAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageAssignmentsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize TokenManager
        tokenManager = TokenManager(requireContext())
        
        // Initialize ViewModels
        val factory = ViewModelFactory(requireContext())
        adminViewModel = ViewModelProvider(this, factory).get(AdminViewModel::class.java)
        assessmentViewModel = ViewModelProvider(this, factory).get(AssessmentViewModel::class.java)
        
        // Setup RecyclerView
        setupRecyclerView()
        
        // Load assignments
        adminViewModel.getAllAssignments()
        
        // Setup click listeners
        setupClickListeners()
        
        // Observe data changes
        observeViewModels()
    }
    
    private fun setupRecyclerView() {
        assignmentAdapter = AssignmentManagementAdapter(
            onEditClick = { assignment ->
                val action = ManageAssignmentsFragmentDirections
                    .actionManageAssignmentsFragmentToEditAssignmentFragment(
                        assignment.courseName, assignment.id
                    )
                findNavController().navigate(action)
            },
            onDeleteClick = { assignment ->
                // Show confirmation dialog before deleting
                showDeleteConfirmationDialog(assignment.courseName, assignment.id)
            },
            onViewClick = { assignment ->
                val action = ManageAssignmentsFragmentDirections
                    .actionManageAssignmentsFragmentToAssignmentDetailsFragment(
                        assignment.courseName, assignment.id
                    )
                findNavController().navigate(action)
            }
        )
        
        binding.rvAssignments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = assignmentAdapter
        }
    }
    
    private fun setupClickListeners() {
        // Search button
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                adminViewModel.searchAssignments(query)
            } else {
                adminViewModel.getAllAssignments()
            }
        }
    }
    
    private fun showDeleteConfirmationDialog(courseName: String, assignmentId: Int) {
        // In a real app, you would show a dialog here
        // For simplicity, we'll just call the delete method directly
        adminViewModel.deleteAssignment(courseName, assignmentId)
    }
    
    private fun observeViewModels() {
        // Observe assignments
        adminViewModel.getAssignments().observe(viewLifecycleOwner) { assignments ->
            if (assignments != null && assignments.isNotEmpty()) {
                assignmentAdapter.submitList(assignments)
                binding.tvNoAssignments.visibility = View.GONE
            } else {
                binding.tvNoAssignments.visibility = View.VISIBLE
            }
        }
        
        // Observe loading state
        adminViewModel.getIsLoading().observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        // Observe error messages
        adminViewModel.getErrorMessage().observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = errorMessage
            } else {
                binding.tvError.visibility = View.GONE
            }
        }
        
        // Observe delete result
        adminViewModel.getAssignmentDeleteResult().observe(viewLifecycleOwner) { result ->
            if (result != null && result) {
                // Refresh assignments list
                adminViewModel.getAllAssignments()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
