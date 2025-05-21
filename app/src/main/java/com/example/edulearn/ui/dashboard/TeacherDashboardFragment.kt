package com.example.edulearn.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.edulearn.R
import com.example.edulearn.databinding.FragmentTeacherDashboardBinding
import com.example.edulearn.utils.TokenManager

class TeacherDashboardFragment : Fragment() {

    private var _binding: FragmentTeacherDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeacherDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get user name from TokenManager
        val userName = TokenManager(requireContext()).getUserName() ?: "Teacher"
        binding.textWelcome.text = "Welcome, $userName"

        // Set up click listeners
        binding.cardCourses.setOnClickListener {
            findNavController().navigate(R.id.coursesFragment)
        }

        binding.cardAssignments.setOnClickListener {
            findNavController().navigate(R.id.assignmentsFragment)
        }

        binding.cardSubmissions.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "Submissions view coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.cardCalendar.setOnClickListener {
            // For now, just show a toast since the calendar fragment isn't implemented yet
            Toast.makeText(requireContext(), "Calendar feature coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.buttonCreateCourse.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "Course creation coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.buttonCreateAssignment.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "Assignment creation coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.buttonLogout.setOnClickListener {
            TokenManager(requireContext()).clearToken()
            findNavController().navigate(R.id.loginFragment)
        }

        // Load dashboard data
        loadDashboardData()
    }

    private fun loadDashboardData() {
        // This would typically come from a ViewModel
        binding.textCourseCount.text = "5"
        binding.textAssignmentCount.text = "12"
        binding.textSubmissionCount.text = "47"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
