package com.example.demo;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private static StudentRepository studentRepository;

    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().add(course);
            course.getStudents().add(student);

            studentRepository.save(student);
        }
    }

    @Transactional
    public void unenrollStudentFromCourse(Long studentId, Long courseId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Course> courseOpt = courseRepository.findById(courseId);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            Student student = studentOpt.get();
            Course course = courseOpt.get();

            student.getCourses().remove(course);
            course.getStudents().remove(student);

            studentRepository.save(student);
        }
    }

    public Set<Course> getCoursesForStudent(Long studentId) {
        return studentRepository.findById(studentId).map(Student::getCourses).orElse(null);
    }

    public Set<Student> getStudentsForCourse(Long courseId) {
        return courseRepository.findById(courseId).map(Course::getStudents).orElse(null);
    }
    public Course addCourse(Course course) {
        // Save the course to the database and return the saved entity
        return courseRepository.save(course);
    }


//    @PostMapping("/api/courses/student")
//    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
//        // logic to add student
//        return ResponseEntity.ok(student);
//    }
}

