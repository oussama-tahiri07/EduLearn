package com.example.edulearn.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.edulearn.R
import com.example.edulearn.databinding.FragmentAdminDashboardBinding
import com.example.edulearn.utils.TokenManager

class AdminDashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get user name from TokenManager
        val userName = TokenManager(requireContext()).getUserName() ?: "Admin"
        binding.textWelcome.text = "Welcome, $userName"

        // Set up click listeners
        binding.cardUsers.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "Users management coming soon", Toast.LENGTH_SHORT).show()
        }

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

        binding.buttonManageUsers.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "User management coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.buttonManageCourses.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "Course management coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.buttonManageAssignments.setOnClickListener {
            // For now, just show a toast since the fragment isn't implemented yet
            Toast.makeText(requireContext(), "Assignment management coming soon", Toast.LENGTH_SHORT).show()
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
        binding.textUserCount.text = "42"
        binding.textCourseCount.text = "15"
        binding.textAssignmentCount.text = "78"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
