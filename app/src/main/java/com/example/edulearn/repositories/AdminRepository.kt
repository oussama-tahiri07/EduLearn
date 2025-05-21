package com.example.edulearn.repositories

import com.example.edulearn.api.ApiService
import com.example.edulearn.models.Assignment
import com.example.edulearn.models.DashboardData
import com.example.edulearn.models.User
import com.example.edulearn.utils.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminRepository(private val tokenManager: TokenManager) {
    
    private val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)
    
    interface AdminCallback<T> {
        fun onSuccess(result: T)
        fun onError(message: String)
    }
    
    // Dashboard data
    fun getDashboardData(callback: AdminCallback<DashboardData>) {
        // This would typically be a single API call, but for now we'll make multiple calls
        // and combine the results
        
        // Get user count
        apiService.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    val userCount = response.body()!!.size
                    
                    // Get course count
                    apiService.getAllCourses().enqueue(object : Callback<List<com.example.edulearn.models.Course>> {
                        override fun onResponse(call: Call<List<com.example.edulearn.models.Course>>, response: Response<List<com.example.edulearn.models.Course>>) {
                            if (response.isSuccessful && response.body() != null) {
                                val courseCount = response.body()!!.size
                                
                                // Get assignment count (this is a placeholder, you'd need to implement this API)
                                // For now, we'll just use a dummy value
                                val assignmentCount = 15
                                
                                // Create dashboard data
                                val dashboardData = DashboardData(
                                    userCount = userCount,
                                    courseCount = courseCount,
                                    assignmentCount = assignmentCount
                                )
                                
                                callback.onSuccess(dashboardData)
                            } else {
                                callback.onError("Failed to get course count: ${response.message()}")
                            }
                        }
                        
                        override fun onFailure(call: Call<List<com.example.edulearn.models.Course>>, t: Throwable) {
                            callback.onError("Network error: ${t.message}")
                        }
                    })
                } else {
                    callback.onError("Failed to get user count: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
    
    // User management
    fun getAllUsers(callback: AdminCallback<List<User>>) {
        apiService.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onError("Failed to get users: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
    
    fun searchUsers(query: String, callback: AdminCallback<List<User>>) {
        apiService.searchUsers(query).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    callback.onError("Failed to search users: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
    
    fun deleteUser(userId: Int, callback: AdminCallback<Boolean>) {
        apiService.deleteUser(userId).enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful) {
                    callback.onSuccess(true)
                } else {
                    callback.onError("Failed to delete user: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
    
    // Course management
    fun deleteCourse(courseId: Int, callback: AdminCallback<Boolean>) {
        apiService.deleteCourse(courseId).enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful) {
                    callback.onSuccess(true)
                } else {
                    callback.onError("Failed to delete course: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
    
    // Assignment management
    fun getAllAssignments(callback: AdminCallback<List<Assignment>>) {
        // This would typically be a single API call, but for now we'll make multiple calls
        // and combine the results
        
        // Get all courses
        apiService.getAllCourses().enqueue(object : Callback<List<com.example.edulearn.models.Course>> {
            override fun onResponse(call: Call<List<com.example.edulearn.models.Course>>, response: Response<List<com.example.edulearn.models.Course>>) {
                if (response.isSuccessful && response.body() != null) {
                    val courses = response.body()!!
                    val allAssignments = mutableListOf<Assignment>()
                    var coursesProcessed = 0
                    
                    // For each course, get its assignments
                    for (course in courses) {
                        apiService.getAssignments(course.name).enqueue(object : Callback<List<Assignment>> {
                            override fun onResponse(call: Call<List<Assignment>>, response: Response<List<Assignment>>) {
                                coursesProcessed++
                                
                                if (response.isSuccessful && response.body() != null) {
                                    allAssignments.addAll(response.body()!!)
                                }
                                
                                // If we've processed all courses, return the result
                                if (coursesProcessed == courses.size) {
                                    callback.onSuccess(allAssignments)
                                }
                            }
                            
                            override fun onFailure(call: Call<List<Assignment>>, t: Throwable) {
                                coursesProcessed++
                                
                                // If we've processed all courses, return the result
                                if (coursesProcessed == courses.size) {
                                    callback.onSuccess(allAssignments)
                                }
                            }
                        })
                    }
                    
                    // If there are no courses, return an empty list
                    if (courses.isEmpty()) {
                        callback.onSuccess(emptyList())
                    }
                } else {
                    callback.onError("Failed to get courses: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<List<com.example.edulearn.models.Course>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
    
    fun searchAssignments(query: String, callback: AdminCallback<List<Assignment>>) {
        // This would typically be a single API call, but for now we'll implement a simple search
        // by getting all assignments and filtering them
        getAllAssignments(object : AdminCallback<List<Assignment>> {
            override fun onSuccess(result: List<Assignment>) {
                val filteredAssignments = result.filter { 
                    it.title.contains(query, ignoreCase = true) || 
                    it.description.contains(query, ignoreCase = true) ||
                    it.courseName.contains(query, ignoreCase = true)
                }
                callback.onSuccess(filteredAssignments)
            }
            
            override fun onError(message: String) {
                callback.onError(message)
            }
        })
    }
    
    fun deleteAssignment(courseName: String, assignmentId: Int, callback: AdminCallback<Boolean>) {
        apiService.deleteAssignment(courseName, assignmentId).enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                if (response.isSuccessful) {
                    callback.onSuccess(true)
                } else {
                    callback.onError("Failed to delete assignment: ${response.message()}")
                }
            }
            
            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                callback.onError("Network error: ${t.message}")
            }
        })
    }
}
