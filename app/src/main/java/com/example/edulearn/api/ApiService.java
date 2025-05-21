package com.example.edulearn.api;

import com.example.edulearn.models.Assignment;
import com.example.edulearn.models.AssignmentSubmission;
import com.example.edulearn.models.Course;
import com.example.edulearn.models.Lesson;
import com.example.edulearn.models.LessonResource;
import com.example.edulearn.models.LoginRequest;
import com.example.edulearn.models.LoginResponse;
import com.example.edulearn.models.Question;
import com.example.edulearn.models.Quiz;
import com.example.edulearn.models.QuizSubmission;
import com.example.edulearn.models.RegisterRequest;
import com.example.edulearn.models.Student;
import com.example.edulearn.models.SubmittedQuestion;
import com.example.edulearn.models.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // Authentication
    @POST("register")
    Call<Map<String, String>> register(@Body RegisterRequest registerRequest);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // User Profile
    @GET("profile")
    Call<User> getUserProfile();

    @PUT("profile")
    Call<User> updateUserProfile(@Body User user);

    @GET("notifications")
    Call<List<Map<String, String>>> getNotifications();

    // Courses
    @GET("get-all-courses")
    Call<List<Course>> getAllCourses();

    @GET("get-my-courses")
    Call<List<Course>> getMyCourses();

    @GET("search-course/{courseName}")
    Call<Course> getCourseByName(@Path("courseName") String courseName);

    @GET("course/{id}")
    Call<Course> getCourseById(@Path("id") int id);

    @POST("create-course")
    Call<Course> createCourse(@Body Course course);

    @POST("course/{courseName}/enroll")
    Call<List<Course>> enrollInCourse(@Path("courseName") String courseName);

    // Lessons
    @GET("course/{courseName}/lessons")
    Call<List<Lesson>> getLessons(@Path("courseName") String courseName);

    @GET("course/{courseName}/lessons/{lessonId}")
    Call<Lesson> getLesson(@Path("courseName") String courseName, @Path("lessonId") int lessonId);

    @POST("course/{courseName}/add-lesson")
    Call<Lesson> addLesson(@Path("courseName") String courseName, @Body Lesson lesson);

    @POST("course/{courseName}/lessons/{lessonId}/generate-OTP")
    Call<Integer> generateOTP(@Path("courseName") String courseName, @Path("lessonId") int lessonId, @Query("duration") int duration);

    @POST("course/{courseName}/lessons/{lessonId}/attendLesson")
    Call<Lesson> attendLesson(@Path("courseName") String courseName, @Path("lessonId") int lessonId, @Query("otp") int otp);

    @GET("course/{courseName}/attended-lessons")
    Call<List<Lesson>> getAttendedLessons(@Path("courseName") String courseName);

    @GET("course/{courseName}/lessons/{lessonId}/attendanceList")
    Call<List<Student>> getAttendanceList(@Path("courseName") String courseName, @Path("lessonId") int lessonId);

    // Lesson Resources
    @GET("course/{courseName}/lessons/{lessonId}/resources")
    Call<List<LessonResource>> getLessonResources(@Path("courseName") String courseName, @Path("lessonId") int lessonId);

    @GET("course/{courseName}/lessons/{lessonId}/resources/{resourceId}")
    Call<ResponseBody> getLessonResource(@Path("courseName") String courseName, @Path("lessonId") int lessonId, @Path("resourceId") int resourceId);

    @Multipart
    @POST("course/{courseName}/lessons/{lessonId}/add-resource")
    Call<String> addLessonResource(@Path("courseName") String courseName, @Path("lessonId") int lessonId, @Part MultipartBody.Part file);

    // Assignments
    @GET("course/{courseName}/assignments")
    Call<List<Assignment>> getAssignments(@Path("courseName") String courseName);

    @GET("course/{courseName}/assignment/{assignmentId}/view")
    Call<Assignment> getAssignment(@Path("courseName") String courseName, @Path("assignmentId") int assignmentId);

    @POST("course/{courseName}/create-assignment")
    Call<Assignment> createAssignment(@Path("courseName") String courseName, @Body Assignment assignment);

    @Multipart
    @POST("course/{courseName}/assignment/{assignmentId}/submit")
    Call<String> submitAssignment(@Path("courseName") String courseName, @Path("assignmentId") int assignmentId, @Part MultipartBody.Part file);

    @GET("course/{courseName}/assignment/{assignmentId}/submissions")
    Call<List<AssignmentSubmission>> getAssignmentSubmissions(@Path("courseName") String courseName, @Path("assignmentId") int assignmentId);

    @GET("course/{courseName}/assignment/{assignmentId}/submission/{submissionId}")
    Call<ResponseBody> getAssignmentSubmission(@Path("courseName") String courseName, @Path("assignmentId") int assignmentId, @Path("submissionId") int submissionId);

    @PUT("course/{courseName}/assignment/{assignmentId}/submission/{submissionId}/grade")
    Call<AssignmentSubmission> gradeAssignment(@Path("courseName") String courseName, @Path("assignmentId") int assignmentId, @Path("submissionId") int submissionId, @Body Map<String, Integer> grade);

    @GET("course/{courseName}/assignment/{assignmentId}/get-grade")
    Call<Map<String, Integer>> getAssignmentGrade(@Path("courseName") String courseName, @Path("assignmentId") int assignmentId);

    // Quizzes
    @POST("course/{courseName}/create-question")
    Call<Void> createQuestion(@Path("courseName") String courseName, @Body Question question);

    @GET("course/{courseName}/get-questions")
    Call<List<Question>> getQuestions(@Path("courseName") String courseName);

    @POST("course/{courseName}/create-quiz")
    Call<Void> createQuiz(@Path("courseName") String courseName, @Body Quiz quiz);

    @GET("course/{courseName}/{quizName}/take-quiz")
    Call<QuizSubmission> takeQuiz(@Path("courseName") String courseName, @Path("quizName") String quizName);

    @POST("course/{courseName}/{quizName}/submit-quiz")
    Call<String> submitQuiz(@Path("courseName") String courseName, @Path("quizName") String quizName, @Body List<SubmittedQuestion> submittedQuestions);

    @GET("course/{courseName}/{quizName}/grade")
    Call<Integer> getQuizGrade(@Path("courseName") String courseName, @Path("quizName") String quizName);

    // Student Management
    @GET("course/{courseName}/enrolled")
    Call<List<Student>> getEnrolledStudents(@Path("courseName") String courseName);

    @DELETE("course/{courseName}/remove-student/{studentId}")
    Call<String> removeStudent(@Path("courseName") String courseName, @Path("studentId") int studentId);
}
