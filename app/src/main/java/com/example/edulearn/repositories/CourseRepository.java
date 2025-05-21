package com.example.edulearn.repositories;

import com.example.edulearn.api.ApiClient;
import com.example.edulearn.api.ApiService;
import com.example.edulearn.models.Course;
import com.example.edulearn.models.Lesson;
import com.example.edulearn.models.LessonResource;
import com.example.edulearn.models.Student;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository {
    private ApiService apiService;
    
    public CourseRepository() {
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }
    
    public interface CourseCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }
    
    // Course methods
    public void getAllCourses(CourseCallback<List<Course>> callback) {
        apiService.getAllCourses().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get courses: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getMyCourses(CourseCallback<List<Course>> callback) {
        apiService.getMyCourses().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get my courses: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getCourseByName(String courseName, CourseCallback<Course> callback) {
        apiService.getCourseByName(courseName).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get course: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void createCourse(Course course, CourseCallback<Course> callback) {
        apiService.createCourse(course).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create course: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void enrollInCourse(String courseName, CourseCallback<List<Course>> callback) {
        apiService.enrollInCourse(courseName).enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to enroll in course: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    // Lesson methods
    public void getLessons(String courseName, CourseCallback<List<Lesson>> callback) {
        apiService.getLessons(courseName).enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get lessons: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getLesson(String courseName, int lessonId, CourseCallback<Lesson> callback) {
        apiService.getLesson(courseName, lessonId).enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get lesson: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void addLesson(String courseName, Lesson lesson, CourseCallback<Lesson> callback) {
        apiService.addLesson(courseName, lesson).enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to add lesson: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void generateOTP(String courseName, int lessonId, int duration, CourseCallback<Integer> callback) {
        apiService.generateOTP(courseName, lessonId, duration).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to generate OTP: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void attendLesson(String courseName, int lessonId, int otp, CourseCallback<Lesson> callback) {
        apiService.attendLesson(courseName, lessonId, otp).enqueue(new Callback<Lesson>() {
            @Override
            public void onResponse(Call<Lesson> call, Response<Lesson> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to attend lesson: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Lesson> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    // Lesson Resources methods
    public void getLessonResources(String courseName, int lessonId, CourseCallback<List<LessonResource>> callback) {
        apiService.getLessonResources(courseName, lessonId).enqueue(new Callback<List<LessonResource>>() {
            @Override
            public void onResponse(Call<List<LessonResource>> call, Response<List<LessonResource>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get lesson resources: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<LessonResource>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getLessonResource(String courseName, int lessonId, int resourceId, CourseCallback<ResponseBody> callback) {
        apiService.getLessonResource(courseName, lessonId, resourceId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get lesson resource: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void addLessonResource(String courseName, int lessonId, MultipartBody.Part file, CourseCallback<String> callback) {
        apiService.addLessonResource(courseName, lessonId, file).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to add lesson resource: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    // Student management methods
    public void getEnrolledStudents(String courseName, CourseCallback<List<Student>> callback) {
        apiService.getEnrolledStudents(courseName).enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get enrolled students: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void removeStudent(String courseName, int studentId, CourseCallback<String> callback) {
        apiService.removeStudent(courseName, studentId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to remove student: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
}
