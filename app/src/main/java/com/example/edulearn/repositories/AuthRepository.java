package com.example.edulearn.repositories;

import com.example.edulearn.api.ApiClient;
import com.example.edulearn.api.ApiService;
import com.example.edulearn.models.LoginRequest;
import com.example.edulearn.models.LoginResponse;
import com.example.edulearn.models.RegisterRequest;
import com.example.edulearn.models.User;
import com.example.edulearn.utils.TokenManager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private ApiService apiService;
    private TokenManager tokenManager;
    
    public AuthRepository(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }
    
    public interface AuthCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }
    
    public void login(String email, String password, String role, AuthCallback<LoginResponse> callback) {
        LoginRequest loginRequest = new LoginRequest(email, password, role);
        apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    tokenManager.saveToken(loginResponse.getToken());
                    tokenManager.saveUserEmail(email);
                    tokenManager.saveUserRole(role);
                    callback.onSuccess(loginResponse);
                } else {
                    callback.onError("Login failed: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void register(String name, String email, String password, String role, AuthCallback<Map<String, String>> callback) {
        RegisterRequest registerRequest = new RegisterRequest(name, email, password, role);
        apiService.register(registerRequest).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Registration failed: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getUserProfile(AuthCallback<User> callback) {
        apiService.getUserProfile().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get profile: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void logout() {
        tokenManager.clearAll();
    }
}
