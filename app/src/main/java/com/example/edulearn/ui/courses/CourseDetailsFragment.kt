package com.example.edulearn.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edulearn.databinding.FragmentCourseDetailsBinding

class CourseDetailsFragment : Fragment() {
    private var _binding: FragmentCourseDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup UI and listeners
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
