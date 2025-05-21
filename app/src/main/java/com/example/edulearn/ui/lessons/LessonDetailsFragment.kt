package com.example.edulearn.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edulearn.databinding.FragmentLessonDetailsBinding

class LessonDetailsFragment : Fragment() {
    private var _binding: FragmentLessonDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonDetailsBinding.inflate(inflater, container, false)
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
