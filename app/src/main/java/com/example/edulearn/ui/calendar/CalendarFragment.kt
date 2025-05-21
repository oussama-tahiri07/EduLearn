package com.example.edulearn.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edulearn.adapters.CalendarEventAdapter
import com.example.edulearn.databinding.FragmentCalendarBinding
import com.example.edulearn.models.CalendarEvent
import com.example.edulearn.utils.TokenManager
import com.example.edulearn.viewmodels.AssessmentViewModel
import com.example.edulearn.viewmodels.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {
    
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var assessmentViewModel: AssessmentViewModel
    private lateinit var tokenManager: TokenManager
    private lateinit var calendarEventAdapter: CalendarEventAdapter
    
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    private val calendar = Calendar.getInstance()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize TokenManager
        tokenManager = TokenManager(requireContext())
        
        // Initialize ViewModel
        val factory = ViewModelFactory(requireContext())
        assessmentViewModel = ViewModelProvider(this, factory).get(AssessmentViewModel::class.java)
        
        // Setup RecyclerView
        setupRecyclerView()
        
        // Setup calendar
        setupCalendar()
        
        // Load events for current month
        loadEventsForCurrentMonth()
        
        // Observe data changes
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        calendarEventAdapter = CalendarEventAdapter { event ->
            // Navigate to assignment details
            val action = CalendarFragmentDirections
                .actionCalendarFragmentToAssignmentDetailsFragment(
                    event.courseName, event.assignmentId
                )
            findNavController().navigate(action)
        }
        
        binding.rvEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = calendarEventAdapter
        }
    }
    
    private fun setupCalendar() {
        // Set current month title
        updateMonthTitle()
        
        // Setup previous month button
        binding.btnPrevMonth.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            updateMonthTitle()
            loadEventsForCurrentMonth()
        }
        
        // Setup next month button
        binding.btnNextMonth.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            updateMonthTitle()
            loadEventsForCurrentMonth()
        }
        
        // Setup calendar view
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            loadEventsForSelectedDate()
        }
    }
    
    private fun updateMonthTitle() {
        binding.tvMonthYear.text = dateFormat.format(calendar.time)
    }
    
    private fun loadEventsForCurrentMonth() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar months are 0-based
        
        val userRole = tokenManager.getUserRole()
        when (userRole) {
            "admin" -> assessmentViewModel.getAllAssignmentsForMonth(year, month)
            "teacher" -> assessmentViewModel.getTeacherAssignmentsForMonth(year, month)
            "student" -> assessmentViewModel.getStudentAssignmentsForMonth(year, month)
        }
    }
    
    private fun loadEventsForSelectedDate() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar months are 0-based
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        
        val userRole = tokenManager.getUserRole()
        when (userRole) {
            "admin" -> assessmentViewModel.getAllAssignmentsForDate(year, month, day)
            "teacher" -> assessmentViewModel.getTeacherAssignmentsForDate(year, month, day)
            "student" -> assessmentViewModel.getStudentAssignmentsForDate(year, month, day)
        }
    }
    
    private fun observeViewModel() {
        // Observe calendar events
        assessmentViewModel.getCalendarEvents().observe(viewLifecycleOwner) { events ->
            if (events != null && events.isNotEmpty()) {
                calendarEventAdapter.submitList(events)
                binding.tvNoEvents.visibility = View.GONE
            } else {
                calendarEventAdapter.submitList(emptyList())
                binding.tvNoEvents.visibility = View.VISIBLE
            }
        }
        
        // Observe loading state
        assessmentViewModel.getIsLoading().observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        // Observe error messages
        assessmentViewModel.getErrorMessage().observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = errorMessage
            } else {
                binding.tvError.visibility = View.GONE
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
