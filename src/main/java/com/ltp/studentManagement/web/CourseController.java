package com.ltp.gradesubmission.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);
        return new ResponseEntity<>(   HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

}
