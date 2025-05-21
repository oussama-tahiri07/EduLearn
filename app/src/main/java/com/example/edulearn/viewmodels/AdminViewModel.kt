package com.example.edulearn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edulearn.models.Assignment
import com.example.edulearn.models.Course
import com.example.edulearn.models.DashboardData
import com.example.edulearn.models.User
import com.example.edulearn.repositories.AdminRepository

class AdminViewModel(private val adminRepository: AdminRepository) : ViewModel() {
    
    private val isLoading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()
    private val dashboardData = MutableLiveData<DashboardData>()
    private val users = MutableLiveData<List<User>>()
    private val assignments = MutableLiveData<List<Assignment>>()
    private val userDeleteResult = MutableLiveData<Boolean>()
    private val courseDeleteResult = MutableLiveData<Boolean>()
    private val assignmentDeleteResult = MutableLiveData<Boolean>()
    
    // Dashboard data
    fun loadDashboardData() {
        isLoading.value = true
        adminRepository.getDashboardData(object : AdminRepository.AdminCallback<DashboardData> {
            override fun onSuccess(result: DashboardData) {
                isLoading.value = false
                dashboardData.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    // User management
    fun getAllUsers() {
        isLoading.value = true
        adminRepository.getAllUsers(object : AdminRepository.AdminCallback<List<User>> {
            override fun onSuccess(result: List<User>) {
                isLoading.value = false
                users.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    fun searchUsers(query: String) {
        isLoading.value = true
        adminRepository.searchUsers(query, object : AdminRepository.AdminCallback<List<User>> {
            override fun onSuccess(result: List<User>) {
                isLoading.value = false
                users.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    fun deleteUser(userId: Int) {
        isLoading.value = true
        adminRepository.deleteUser(userId, object : AdminRepository.AdminCallback<Boolean> {
            override fun onSuccess(result: Boolean) {
                isLoading.value = false
                userDeleteResult.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    // Course management
    fun deleteCourse(courseId: Int) {
        isLoading.value = true
        adminRepository.deleteCourse(courseId, object : AdminRepository.AdminCallback<Boolean> {
            override fun onSuccess(result: Boolean) {
                isLoading.value = false
                courseDeleteResult.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    // Assignment management
    fun getAllAssignments() {
        isLoading.value = true
        adminRepository.getAllAssignments(object : AdminRepository.AdminCallback<List<Assignment>> {
            override fun onSuccess(result: List<Assignment>) {
                isLoading.value = false
                assignments.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    fun searchAssignments(query: String) {
        isLoading.value = true
        adminRepository.searchAssignments(query, object : AdminRepository.AdminCallback<List<Assignment>> {
            override fun onSuccess(result: List<Assignment>) {
                isLoading.value = false
                assignments.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    fun deleteAssignment(courseName: String, assignmentId: Int) {
        isLoading.value = true
        adminRepository.deleteAssignment(courseName, assignmentId, object : AdminRepository.AdminCallback<Boolean> {
            override fun onSuccess(result: Boolean) {
                isLoading.value = false
                assignmentDeleteResult.value = result
            }
            
            override fun onError(message: String) {
                isLoading.value = false
                errorMessage.value = message
            }
        })
    }
    
    // Getters for LiveData
    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getErrorMessage(): LiveData<String> = errorMessage
    fun getDashboardData(): LiveData<DashboardData> = dashboardData
    fun getUsers(): LiveData<List<User>> = users
    fun getAssignments(): LiveData<List<Assignment>> = assignments
    fun getUserDeleteResult(): LiveData<Boolean> = userDeleteResult
    fun getCourseDeleteResult(): LiveData<Boolean> = courseDeleteResult
    fun getAssignmentDeleteResult(): LiveData<Boolean> = assignmentDeleteResult
}
