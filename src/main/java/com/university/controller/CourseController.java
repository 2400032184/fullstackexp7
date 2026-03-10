package com.university.controller;

import com.university.entity.Course;
import com.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> addCourse(@RequestBody Course course) {
        Map<String, Object> response = new HashMap<>();
        
        if (course.getTitle() == null || course.getTitle().isEmpty() || 
            course.getDuration() == null || course.getFee() == null) {
            response.put("message", "Invalid course data");
            response.put("status", "BAD_REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        Course savedCourse = courseService.addCourse(course);
        response.put("message", "Course created successfully");
        response.put("status", "CREATED");
        response.put("data", savedCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = new HashMap<>();
        List<Course> courses = courseService.getAllCourses();
        
        if (courses.isEmpty()) {
            response.put("message", "No courses found");
            response.put("status", "OK");
            response.put("data", courses);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        response.put("message", "Courses retrieved successfully");
        response.put("status", "OK");
        response.put("data", courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<Map<String, Object>> getCourseById(@PathVariable Long courseId) {
        Map<String, Object> response = new HashMap<>();
        Optional<Course> course = courseService.getCourseById(courseId);
        
        if (course.isPresent()) {
            response.put("message", "Course retrieved successfully");
            response.put("status", "OK");
            response.put("data", course.get());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        response.put("message", "Course not found");
        response.put("status", "NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @PutMapping("/{courseId}")
    public ResponseEntity<Map<String, Object>> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        Map<String, Object> response = new HashMap<>();
        
        if (course.getTitle() == null || course.getTitle().isEmpty() || 
            course.getDuration() == null || course.getFee() == null) {
            response.put("message", "Invalid course data");
            response.put("status", "BAD_REQUEST");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        Course updatedCourse = courseService.updateCourse(courseId, course);
        
        if (updatedCourse != null) {
            response.put("message", "Course updated successfully");
            response.put("status", "OK");
            response.put("data", updatedCourse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        response.put("message", "Course not found");
        response.put("status", "NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable Long courseId) {
        Map<String, Object> response = new HashMap<>();
        
        if (courseService.deleteCourse(courseId)) {
            response.put("message", "Course deleted successfully");
            response.put("status", "OK");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        response.put("message", "Course not found");
        response.put("status", "NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @GetMapping("/search/{title}")
    public ResponseEntity<Map<String, Object>> searchCoursesByTitle(@PathVariable String title) {
        Map<String, Object> response = new HashMap<>();
        List<Course> courses = courseService.searchCoursesByTitle(title);
        
        if (courses.isEmpty()) {
            response.put("message", "No courses found with title: " + title);
            response.put("status", "NOT_FOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        response.put("message", "Courses found");
        response.put("status", "OK");
        response.put("data", courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
