package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course createdCourse = courseService.addCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    @PostMapping("/{courseId}/students/{studentId}/enroll")
    public String enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.enrollStudentInCourse(studentId, courseId);
        return "Student enrolled in course successfully!";
    }

//    @PostMapping
//    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
//        Student createdStudent = CourseService.addStudent(student);
//        return ResponseEntity.ok(createdStudent);
//    }
    @PostMapping("/{courseId}/students/{studentId}/unenroll")
    public String unenrollStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.unenrollStudentFromCourse(studentId, courseId);
        return "Student unenrolled from course successfully!";
    }

    @GetMapping("/{studentId}/courses")
    public Set<Course> getCoursesForStudent(@PathVariable Long studentId) {
        return courseService.getCoursesForStudent(studentId);
    }

    @GetMapping("/{courseId}/students")
    public Set<Student> getStudentsForCourse(@PathVariable Long courseId) {
        return courseService.getStudentsForCourse(courseId);
    }
}
