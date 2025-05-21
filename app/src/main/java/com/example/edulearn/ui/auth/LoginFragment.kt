package com.example.edulearn.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.edulearn.R
import com.example.edulearn.databinding.FragmentLoginBinding
import com.example.edulearn.repositories.AuthRepository
import com.example.edulearn.utils.TokenManager
import com.example.edulearn.viewmodels.AuthViewModel
import com.example.edulearn.viewmodels.AuthViewModelFactory

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize TokenManager
        tokenManager = TokenManager(requireContext())

        // Setup ViewModel with factory
        val authRepository = AuthRepository(tokenManager)
        val viewModelFactory = AuthViewModelFactory(authRepository)
        authViewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]

        // Check if user is already logged in
        if (tokenManager.hasToken()) {
            navigateBasedOnRole()
            return
        }

        // Setup role dropdown
        val roles = arrayOf(getString(R.string.student), getString(R.string.instructor), getString(R.string.admin))
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, roles)
        binding.dropdownRole.setAdapter(adapter)
        binding.dropdownRole.setText(roles[0], false) // Default to Student

        // Setup click listeners
        binding.btnLogin.setOnClickListener { login() }
        binding.tvRegister.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        // Observe ViewModel
        authViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLogin.isEnabled = !isLoading
        }

        authViewModel.loginResult.observe(viewLifecycleOwner) { loginResponse ->
            if (loginResponse != null) {
                // Save user role and name
                tokenManager.saveUserRole(loginResponse.user.role)
                tokenManager.saveUserName("${loginResponse.user.firstName} ${loginResponse.user.lastName}")

                navigateBasedOnRole()
            }
        }

        authViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val role = binding.dropdownRole.text.toString().trim()

        // Validate inputs
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email is required"
            return
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            return
        }

        if (role.isEmpty()) {
            Toast.makeText(requireContext(), "Please select a role", Toast.LENGTH_SHORT).show()
            return
        }

        // Clear errors
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        // Login
        authViewModel.login(email, password, role)
    }

    private fun navigateBasedOnRole() {
        // For now, navigate to home fragment
        // In a real implementation, you would navigate to different dashboards based on role
        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
