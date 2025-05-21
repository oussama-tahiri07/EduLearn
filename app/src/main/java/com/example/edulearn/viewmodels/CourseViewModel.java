package com.example.edulearn.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.edulearn.models.Course;
import com.example.edulearn.models.Lesson;
import com.example.edulearn.models.LessonResource;
import com.example.edulearn.models.Student;
import com.example.edulearn.repositories.CourseRepository;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public class CourseViewModel extends ViewModel {
    private CourseRepository courseRepository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<List<Course>> allCourses = new MutableLiveData<>();
    private MutableLiveData<List<Course>> myCourses = new MutableLiveData<>();
    private MutableLiveData<Course> course = new MutableLiveData<>();
    private MutableLiveData<List<Lesson>> lessons = new MutableLiveData<>();
    private MutableLiveData<Lesson> lesson = new MutableLiveData<>();
    private MutableLiveData<Integer> otpCode = new MutableLiveData<>();
    private MutableLiveData<List<LessonResource>> lessonResources = new MutableLiveData<>();
    private MutableLiveData<ResponseBody> lessonResource = new MutableLiveData<>();
    private MutableLiveData<List<Student>> enrolledStudents = new MutableLiveData<>();

    public CourseViewModel(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Course methods
    public void fetchAllCourses() {
        isLoading.setValue(true);
        courseRepository.getAllCourses(new CourseRepository.CourseCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> result) {
                isLoading.setValue(false);
                allCourses.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void fetchMyCourses() {
        isLoading.setValue(true);
        courseRepository.getMyCourses(new CourseRepository.CourseCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> result) {
                isLoading.setValue(false);
                myCourses.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void fetchCourseByName(String courseName) {
        isLoading.setValue(true);
        courseRepository.getCourseByName(courseName, new CourseRepository.CourseCallback<Course>() {
            @Override
            public void onSuccess(Course result) {
                isLoading.setValue(false);
                course.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void createCourse(Course newCourse) {
        isLoading.setValue(true);
        courseRepository.createCourse(newCourse, new CourseRepository.CourseCallback<Course>() {
            @Override
            public void onSuccess(Course result) {
                isLoading.setValue(false);
                course.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void enrollInCourse(String courseName) {
        isLoading.setValue(true);
        courseRepository.enrollInCourse(courseName, new CourseRepository.CourseCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> result) {
                isLoading.setValue(false);
                myCourses.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    // Lesson methods
    public void fetchLessons(String courseName) {
        isLoading.setValue(true);
        courseRepository.getLessons(courseName, new CourseRepository.CourseCallback<List<Lesson>>() {
            @Override
            public void onSuccess(List<Lesson> result) {
                isLoading.setValue(false);
                lessons.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void fetchLesson(String courseName, int lessonId) {
        isLoading.setValue(true);
        courseRepository.getLesson(courseName, lessonId, new CourseRepository.CourseCallback<Lesson>() {
            @Override
            public void onSuccess(Lesson result) {
                isLoading.setValue(false);
                lesson.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void addLesson(String courseName, Lesson newLesson) {
        isLoading.setValue(true);
        courseRepository.addLesson(courseName, newLesson, new CourseRepository.CourseCallback<Lesson>() {
            @Override
            public void onSuccess(Lesson result) {
                isLoading.setValue(false);
                lesson.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void generateOTP(String courseName, int lessonId, int duration) {
        isLoading.setValue(true);
        courseRepository.generateOTP(courseName, lessonId, duration, new CourseRepository.CourseCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                isLoading.setValue(false);
                otpCode.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void attendLesson(String courseName, int lessonId, int otp) {
        isLoading.setValue(true);
        courseRepository.attendLesson(courseName, lessonId, otp, new CourseRepository.CourseCallback<Lesson>() {
            @Override
            public void onSuccess(Lesson result) {
                isLoading.setValue(false);
                lesson.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    // Lesson Resources methods
    public void fetchLessonResources(String courseName, int lessonId) {
        isLoading.setValue(true);
        courseRepository.getLessonResources(courseName, lessonId, new CourseRepository.CourseCallback<List<LessonResource>>() {
            @Override
            public void onSuccess(List<LessonResource> result) {
                isLoading.setValue(false);
                lessonResources.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void fetchLessonResource(String courseName, int lessonId, int resourceId) {
        isLoading.setValue(true);
        courseRepository.getLessonResource(courseName, lessonId, resourceId, new CourseRepository.CourseCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                isLoading.setValue(false);
                lessonResource.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void addLessonResource(String courseName, int lessonId, MultipartBody.Part file) {
        isLoading.setValue(true);
        courseRepository.addLessonResource(courseName, lessonId, file, new CourseRepository.CourseCallback<String>() {
            @Override
            public void onSuccess(String result) {
                isLoading.setValue(false);
                // Refresh resources after adding
                fetchLessonResources(courseName, lessonId);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    // Student management methods
    public void fetchEnrolledStudents(String courseName) {
        isLoading.setValue(true);
        courseRepository.getEnrolledStudents(courseName, new CourseRepository.CourseCallback<List<Student>>() {
            @Override
            public void onSuccess(List<Student> result) {
                isLoading.setValue(false);
                enrolledStudents.setValue(result);
            }

            @Override
            public void onError(String message) {
                isLoading.setValue(false);
                errorMessage.setValue(message);
            }
        });
    }

    public void removeStudent(String courseName, int studentId) {
        isLoading.setValue(true);
        courseRepository.removeStudent(courseName, studentId, new CourseRepository.CourseCallback<String>() {
            @Override
            public void onSuccess(String result) {
                isLoading.setValue(false);
                // Refresh enrolled students after removing
                fetchEnrolledStudents(courseName);
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

    public LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public LiveData<List<Course>> getMyCourses() {
        return myCourses;
    }

    public LiveData<Course> getCourse() {
        return course;
    }

    public LiveData<List<Lesson>> getLessons() {
        return lessons;
    }

    public LiveData<Lesson> getLesson() {
        return lesson;
    }

    public LiveData<Integer> getOtpCode() {
        return otpCode;
    }

    public LiveData<List<LessonResource>> getLessonResources() {
        return lessonResources;
    }

    public LiveData<ResponseBody> getLessonResource() {
        return lessonResource;
    }

    public LiveData<List<Student>> getEnrolledStudents() {
        return enrolledStudents;
    }
}
