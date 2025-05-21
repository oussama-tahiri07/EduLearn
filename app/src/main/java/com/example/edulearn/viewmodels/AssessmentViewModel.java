package com.example.edulearn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edulearn.models.Assignment;
import com.example.edulearn.models.AssignmentSubmission;
import com.example.edulearn.models.Question;
import com.example.edulearn.models.Quiz;
import com.example.edulearn.models.QuizSubmission;
import com.example.edulearn.models.SubmittedQuestion;
import com.example.edulearn.repositories.AssessmentRepository;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public class AssessmentViewModel extends ViewModel {
    private AssessmentRepository assessmentRepository;
    
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<List<Assignment>> assignments = new MutableLiveData<>();
    private MutableLiveData<Assignment> assignment = new MutableLiveData<>();
    private MutableLiveData<List<AssignmentSubmission>> assignmentSubmissions = new MutableLiveData<>();
    private MutableLiveData<ResponseBody> assignmentSubmission = new MutableLiveData<>();
    private MutableLiveData<Integer> assignmentGrade = new MutableLiveData<>();
    private MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private MutableLiveData<QuizSubmission> quizSubmission = new MutableLiveData<>();
    private MutableLiveData<Integer> quizGrade = new MutableLiveData<>();
    
    public AssessmentViewModel(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }
    
    // Assignment methods
    public void getAssignments(String courseName) {
        isLoading.setValue(true);
        assessmentRepository.getAssignments(courseName, new AssessmentRepository.AssessmentCallback<List<Assignment>>() {
            @Override
            public void onSuccess(List<Assignment> result) {
                isLoading.setValue(false);
                assignments.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void getAssignment(String courseName, int assignmentId) {
        isLoading.setValue(true);
        assessmentRepository.getAssignment(courseName, assignmentId, new AssessmentRepository.AssessmentCallback<Assignment>() {
            @Override
            public void onSuccess(Assignment result) {
                isLoading.setValue(false);
                assignment.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void createAssignment(String courseName, Assignment newAssignment) {
        isLoading.setValue(true);
        assessmentRepository.createAssignment(courseName, newAssignment, new AssessmentRepository.AssessmentCallback<Assignment>() {
            @Override
            public void onSuccess(Assignment result) {
                isLoading.setValue(false);
                assignment.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void submitAssignment(String courseName, int assignmentId, MultipartBody.Part file) {
        isLoading.setValue(true);
        assessmentRepository.submitAssignment(courseName, assignmentId, file, new AssessmentRepository.AssessmentCallback<String>() {
            @Override
            public void onSuccess(String result) {
                isLoading.setValue(false);
                // Refresh assignment after submission
                getAssignment(courseName, assignmentId);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void getAssignmentSubmissions(String courseName, int assignmentId) {
        isLoading.setValue(true);
        assessmentRepository.getAssignmentSubmissions(courseName, assignmentId, new AssessmentRepository.AssessmentCallback<List<AssignmentSubmission>>() {
            @Override
            public void onSuccess(List<AssignmentSubmission> result) {
                isLoading.setValue(false);
                assignmentSubmissions.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void getAssignmentSubmission(String courseName, int assignmentId, int submissionId) {
        isLoading.setValue(true);
        assessmentRepository.getAssignmentSubmission(courseName, assignmentId, submissionId, new AssessmentRepository.AssessmentCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                isLoading.setValue(false);
                assignmentSubmission.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void gradeAssignment(String courseName, int assignmentId, int submissionId, int grade) {
        isLoading.setValue(true);
        assessmentRepository.gradeAssignment(courseName, assignmentId, submissionId, grade, new AssessmentRepository.AssessmentCallback<AssignmentSubmission>() {
            @Override
            public void onSuccess(AssignmentSubmission result) {
                isLoading.setValue(false);
                // Refresh submissions after grading
                getAssignmentSubmissions(courseName, assignmentId);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    // Quiz methods
    public void createQuestion(String courseName, Question question) {
        isLoading.setValue(true);
        assessmentRepository.createQuestion(courseName, question, new AssessmentRepository.AssessmentCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                isLoading.setValue(false);
                // Refresh questions after creating
                getQuestions(courseName);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void getQuestions(String courseName) {
        isLoading.setValue(true);
        assessmentRepository.getQuestions(courseName, new AssessmentRepository.AssessmentCallback<List<Question>>() {
            @Override
            public void onSuccess(List<Question> result) {
                isLoading.setValue(false);
                questions.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void createQuiz(String courseName, Quiz quiz) {
        isLoading.setValue(true);
        assessmentRepository.createQuiz(courseName, quiz, new AssessmentRepository.AssessmentCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                isLoading.setValue(false);
                // No specific action needed after creating quiz
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void takeQuiz(String courseName, String quizName) {
        isLoading.setValue(true);
        assessmentRepository.takeQuiz(courseName, quizName, new AssessmentRepository.AssessmentCallback<QuizSubmission>() {
            @Override
            public void onSuccess(QuizSubmission result) {
                isLoading.setValue(false);
                quizSubmission.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void submitQuiz(String courseName, String quizName, List<SubmittedQuestion> submittedQuestions) {
        isLoading.setValue(true);
        assessmentRepository.submitQuiz(courseName, quizName, submittedQuestions, new AssessmentRepository.AssessmentCallback<String>() {
            @Override
            public void onSuccess(String result) {
                isLoading.setValue(false);
                // Get grade after submission
                getQuizGrade(courseName, quizName);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    public void getQuizGrade(String courseName, String quizName) {
        isLoading.setValue(true);
        assessmentRepository.getQuizGrade(courseName, quizName, new AssessmentRepository.AssessmentCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                isLoading.setValue(false);
                quizGrade.setValue(result);
            }
            
            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }
    
    // LiveData getters
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }
    
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    
    public LiveData<List<Assignment>> getAssignments() {
        return assignments;
    }
    
    public LiveData<Assignment> getAssignment() {
        return assignment;
    }
    
    public LiveData<List<AssignmentSubmission>> getAssignmentSubmissions() {
        return assignmentSubmissions;
    }
    
    public LiveData<ResponseBody> getAssignmentSubmission() {
        return assignmentSubmission;
    }
    
    public LiveData<Integer> getAssignmentGrade() {
        return assignmentGrade;
    }
    
    public LiveData<List<Question>> getQuestions() {
        return questions;
    }
    
    public LiveData<QuizSubmission> getQuizSubmission() {
        return quizSubmission;
    }
    
    public LiveData<Integer> getQuizGrade() {
        return quizGrade;
    }
}
