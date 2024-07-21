package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.exceptions.CourseNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course getCourse(Long id) {
        return null;
    }

    @Override
    public Course findById(Long id) {
        // TODO Auto-generated method stub

        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            return course.get();
        } else {
            throw new CourseNotFoundException(id);
        }

    }

    @Override
    public List<Course> findAll() {

        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {

        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }

}
