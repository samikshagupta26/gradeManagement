package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exceptions.GradeNotFoundException;
import com.ltp.gradesubmission.repository.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {

        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);

        if (grade.isPresent()) {
            return grade.get();
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }

    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Student student = studentService.getStudent(studentId);

        Course course = courseService.findById(courseId);

        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {

        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);

        if (grade.isPresent()) {

            grade.get().setScore(score);

            return gradeRepository.save(grade.get());
        }
        throw new GradeNotFoundException(studentId, courseId);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {

        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);

    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List) gradeRepository.findAll();
    }

}
