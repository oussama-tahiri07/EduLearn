package com.example.edulearn.ui.assignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edulearn.databinding.FragmentAssignmentDetailsBinding

class AssignmentDetailsFragment : Fragment() {
    private var _binding: FragmentAssignmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssignmentDetailsBinding.inflate(inflater, container, false)
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
