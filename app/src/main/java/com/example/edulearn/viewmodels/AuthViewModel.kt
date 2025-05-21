package com.example.edulearn.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edulearn.models.LoginResponse
import com.example.edulearn.models.User
import com.example.edulearn.repositories.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    private val _errorMessage = MutableLiveData<String>()
    private val _loginResult = MutableLiveData<LoginResponse>()
    private val _registerResult = MutableLiveData<Map<String, String>>()
    private val _userProfile = MutableLiveData<User>()

    val isLoading: LiveData<Boolean> = _isLoading
    val errorMessage: LiveData<String> = _errorMessage
    val loginResult: LiveData<LoginResponse> = _loginResult
    val registerResult: LiveData<Map<String, String>> = _registerResult
    val userProfile: LiveData<User> = _userProfile

    fun login(email: String, password: String, role: String) {
        _isLoading.value = true
        authRepository.login(email, password, role, object : AuthRepository.AuthCallback<LoginResponse> {
            override fun onSuccess(result: LoginResponse) {
                _isLoading.postValue(false)
                _loginResult.postValue(result)
            }

            override fun onError(message: String) {
                _isLoading.postValue(false)
                _errorMessage.postValue(message)
            }
        })
    }

    fun register(name: String, email: String, password: String, role: String) {
        _isLoading.value = true
        authRepository.register(name, email, password, role, object : AuthRepository.AuthCallback<Map<String, String>> {
            override fun onSuccess(result: Map<String, String>) {
                _isLoading.postValue(false)
                _registerResult.postValue(result)
            }

            override fun onError(message: String) {
                _isLoading.postValue(false)
                _errorMessage.postValue(message)
            }
        })
    }

    fun fetchUserProfile() {
        _isLoading.value = true
        authRepository.getUserProfile(object : AuthRepository.AuthCallback<User> {
            override fun onSuccess(result: User) {
                _isLoading.postValue(false)
                _userProfile.postValue(result)
            }

            override fun onError(message: String) {
                _isLoading.postValue(false)
                _errorMessage.postValue(message)
            }
        })
    }

    fun logout() {
        authRepository.logout()
    }
}
