package com.example.edulearn.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edulearn.R
import com.example.edulearn.adapters.CourseManagementAdapter
import com.example.edulearn.databinding.FragmentManageCoursesBinding
import com.example.edulearn.utils.TokenManager
import com.example.edulearn.viewmodels.AdminViewModel
import com.example.edulearn.viewmodels.CourseViewModel
import com.example.edulearn.viewmodels.ViewModelFactory

class ManageCoursesFragment : Fragment() {
    
    private var _binding: FragmentManageCoursesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var tokenManager: TokenManager
    private lateinit var courseAdapter: CourseManagementAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize TokenManager
        tokenManager = TokenManager(requireContext())
        
        // Initialize ViewModels
        val factory = ViewModelFactory(requireContext())
        adminViewModel = ViewModelProvider(this, factory).get(AdminViewModel::class.java)
        courseViewModel = ViewModelProvider(this, factory).get(CourseViewModel::class.java)
        
        // Setup RecyclerView
        setupRecyclerView()
        
        // Load courses
        courseViewModel.getAllCourses()
        
        // Setup click listeners
        setupClickListeners()
        
        // Observe data changes
        observeViewModels()
    }
    
    private fun setupRecyclerView() {
        courseAdapter = CourseManagementAdapter(
            onEditClick = { course ->
                val action = ManageCoursesFragmentDirections
                    .actionManageCoursesFragmentToEditCourseFragment(course.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { course ->
                // Show confirmation dialog before deleting
                showDeleteConfirmationDialog(course.id)
            },
            onAssignClick = { course ->
                val action = ManageCoursesFragmentDirections
                    .actionManageCoursesFragmentToAssignCourseFragment(course.id)
                findNavController().navigate(action)
            }
        )
        
        binding.rvCourses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = courseAdapter
        }
    }
    
    private fun setupClickListeners() {
        // Add Course button
        binding.fabAddCourse.setOnClickListener {
            findNavController().navigate(R.id.action_manageCoursesFragment_to_createCourseFragment)
        }
        
        // Search button
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                courseViewModel.searchCourses(query)
            } else {
                courseViewModel.getAllCourses()
            }
        }
    }
    
    private fun showDeleteConfirmationDialog(courseId: Int) {
        // In a real app, you would show a dialog here
        // For simplicity, we'll just call the delete method directly
        adminViewModel.deleteCourse(courseId)
    }
    
    private fun observeViewModels() {
        // Observe courses
        courseViewModel.getAllCourses().observe(viewLifecycleOwner) { courses ->
            if (courses != null && courses.isNotEmpty()) {
                courseAdapter.submitList(courses)
                binding.tvNoCourses.visibility = View.GONE
            } else {
                binding.tvNoCourses.visibility = View.VISIBLE
            }
        }
        
        // Observe loading state
        courseViewModel.getIsLoading().observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        // Observe error messages
        courseViewModel.getErrorMessage().observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = errorMessage
            } else {
                binding.tvError.visibility = View.GONE
            }
        }
        
        // Observe delete result
        adminViewModel.getCourseDeleteResult().observe(viewLifecycleOwner) { result ->
            if (result != null && result) {
                // Refresh courses list
                courseViewModel.getAllCourses()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
