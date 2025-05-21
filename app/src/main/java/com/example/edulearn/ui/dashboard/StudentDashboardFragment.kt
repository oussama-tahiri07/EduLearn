package com.example.edulearn.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.edulearn.R
import com.example.edulearn.databinding.FragmentStudentDashboardBinding
import com.example.edulearn.utils.TokenManager

class StudentDashboardFragment : Fragment() {

    private var _binding: FragmentStudentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get user name from TokenManager
        val userName = TokenManager(requireContext()).getUserName() ?: "Student"
        binding.textWelcome.text = "Welcome, $userName"

        // Set up click listeners
        binding.cardCourses.setOnClickListener {
            findNavController().navigate(R.id.coursesFragment)
        }

        binding.cardAssignments.setOnClickListener {
            findNavController().navigate(R.id.assignmentsFragment)
        }

        binding.cardCalendar.setOnClickListener {
            // For now, just show a toast since the calendar fragment isn't implemented yet
            Toast.makeText(requireContext(), "Calendar feature coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.buttonEnroll.setOnClickListener {
            val courseCode = binding.editCourseCode.text?.toString()?.trim() ?: ""
            if (courseCode.isNotEmpty()) {
                enrollInCourse(courseCode)
            } else {
                Toast.makeText(requireContext(), "Please enter a course code", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonLogout.setOnClickListener {
            TokenManager(requireContext()).clearToken()
            findNavController().navigate(R.id.loginFragment)
        }

        // Load dashboard data
        loadDashboardData()
    }

    private fun enrollInCourse(courseCode: String) {
        // This would typically be handled by a ViewModel
        Toast.makeText(requireContext(), "Enrolling in course: $courseCode", Toast.LENGTH_SHORT).show()
        binding.editCourseCode.text?.clear()
    }

    private fun loadDashboardData() {
        // This would typically come from a ViewModel
        binding.textCourseCount.text = "3"
        binding.textAssignmentCount.text = "7"
        binding.textUpcomingCount.text = "2"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
