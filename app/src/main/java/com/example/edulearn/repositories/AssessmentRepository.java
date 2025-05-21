package com.example.edulearn.repositories;

import com.example.edulearn.api.ApiClient;
import com.example.edulearn.api.ApiService;
import com.example.edulearn.models.Assignment;
import com.example.edulearn.models.AssignmentSubmission;
import com.example.edulearn.models.Question;
import com.example.edulearn.models.Quiz;
import com.example.edulearn.models.QuizSubmission;
import com.example.edulearn.models.SubmittedQuestion;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssessmentRepository {
    private ApiService apiService;
    
    public AssessmentRepository() {
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }
    
    public interface AssessmentCallback<T> {
        void onSuccess(T result);
        void onError(String message);
    }
    
    // Assignment methods
    public void getAssignments(String courseName, AssessmentCallback<List<Assignment>> callback) {
        apiService.getAssignments(courseName).enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get assignments: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getAssignment(String courseName, int assignmentId, AssessmentCallback<Assignment> callback) {
        apiService.getAssignment(courseName, assignmentId).enqueue(new Callback<Assignment>() {
            @Override
            public void onResponse(Call<Assignment> call, Response<Assignment> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get assignment: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Assignment> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void createAssignment(String courseName, Assignment assignment, AssessmentCallback<Assignment> callback) {
        apiService.createAssignment(courseName, assignment).enqueue(new Callback<Assignment>() {
            @Override
            public void onResponse(Call<Assignment> call, Response<Assignment> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to create assignment: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Assignment> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void submitAssignment(String courseName, int assignmentId, MultipartBody.Part file, AssessmentCallback<String> callback) {
        apiService.submitAssignment(courseName, assignmentId, file).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to submit assignment: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getAssignmentSubmissions(String courseName, int assignmentId, AssessmentCallback<List<AssignmentSubmission>> callback) {
        apiService.getAssignmentSubmissions(courseName, assignmentId).enqueue(new Callback<List<AssignmentSubmission>>() {
            @Override
            public void onResponse(Call<List<AssignmentSubmission>> call, Response<List<AssignmentSubmission>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get assignment submissions: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<AssignmentSubmission>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getAssignmentSubmission(String courseName, int assignmentId, int submissionId, AssessmentCallback<ResponseBody> callback) {
        apiService.getAssignmentSubmission(courseName, assignmentId, submissionId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get assignment submission: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void gradeAssignment(String courseName, int assignmentId, int submissionId, int grade, AssessmentCallback<AssignmentSubmission> callback) {
        Map<String, Integer> gradeMap = Map.of("grade", grade);
        apiService.gradeAssignment(courseName, assignmentId, submissionId, gradeMap).enqueue(new Callback<AssignmentSubmission>() {
            @Override
            public void onResponse(Call<AssignmentSubmission> call, Response<AssignmentSubmission> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to grade assignment: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<AssignmentSubmission> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    // Quiz methods
    public void createQuestion(String courseName, Question question, AssessmentCallback<Void> callback) {
        apiService.createQuestion(courseName, question).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to create question: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getQuestions(String courseName, AssessmentCallback<List<Question>> callback) {
        apiService.getQuestions(courseName).enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get questions: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void createQuiz(String courseName, Quiz quiz, AssessmentCallback<Void> callback) {
        apiService.createQuiz(courseName, quiz).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Failed to create quiz: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void takeQuiz(String courseName, String quizName, AssessmentCallback<QuizSubmission> callback) {
        apiService.takeQuiz(courseName, quizName).enqueue(new Callback<QuizSubmission>() {
            @Override
            public void onResponse(Call<QuizSubmission> call, Response<QuizSubmission> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to take quiz: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<QuizSubmission> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void submitQuiz(String courseName, String quizName, List<SubmittedQuestion> submittedQuestions, AssessmentCallback<String> callback) {
        apiService.submitQuiz(courseName, quizName, submittedQuestions).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to submit quiz: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
    
    public void getQuizGrade(String courseName, String quizName, AssessmentCallback<Integer> callback) {
        apiService.getQuizGrade(courseName, quizName).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Failed to get quiz grade: " + (response.errorBody() != null ? response.errorBody().toString() : "Unknown error"));
                }
            }
            
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                callback.onError("Network error: " + t.getMessage());
            }
        });
    }
}
