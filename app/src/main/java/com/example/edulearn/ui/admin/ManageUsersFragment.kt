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
import com.example.edulearn.adapters.UserAdapter
import com.example.edulearn.databinding.FragmentManageUsersBinding
import com.example.edulearn.utils.TokenManager
import com.example.edulearn.viewmodels.AdminViewModel
import com.example.edulearn.viewmodels.ViewModelFactory

class ManageUsersFragment : Fragment() {
    
    private var _binding: FragmentManageUsersBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var tokenManager: TokenManager
    private lateinit var userAdapter: UserAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageUsersBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize TokenManager
        tokenManager = TokenManager(requireContext())
        
        // Initialize ViewModel
        val factory = ViewModelFactory(requireContext())
        adminViewModel = ViewModelProvider(this, factory).get(AdminViewModel::class.java)
        
        // Setup RecyclerView
        setupRecyclerView()
        
        // Load users
        adminViewModel.getAllUsers()
        
        // Setup click listeners
        setupClickListeners()
        
        // Observe data changes
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        userAdapter = UserAdapter(
            onEditClick = { user ->
                val action = ManageUsersFragmentDirections
                    .actionManageUsersFragmentToEditUserFragment(user.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { user ->
                // Show confirmation dialog before deleting
                showDeleteConfirmationDialog(user.id)
            }
        )
        
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
    }
    
    private fun setupClickListeners() {
        // Add User button
        binding.fabAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_manageUsersFragment_to_addUserFragment)
        }
        
        // Search button
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                adminViewModel.searchUsers(query)
            } else {
                adminViewModel.getAllUsers()
            }
        }
    }
    
    private fun showDeleteConfirmationDialog(userId: Int) {
        // In a real app, you would show a dialog here
        // For simplicity, we'll just call the delete method directly
        adminViewModel.deleteUser(userId)
    }
    
    private fun observeViewModel() {
        // Observe users
        adminViewModel.getUsers().observe(viewLifecycleOwner) { users ->
            if (users != null && users.isNotEmpty()) {
                userAdapter.submitList(users)
                binding.tvNoUsers.visibility = View.GONE
            } else {
                binding.tvNoUsers.visibility = View.VISIBLE
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
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
