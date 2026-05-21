package com.example.studentApi.services;

import com.example.studentApi.dto.CourseRequestDto;
import com.example.studentApi.dto.CourseResponseDto;
import com.example.studentApi.entity.Course;
import com.example.studentApi.exception.StudentNotFoundException;
import com.example.studentApi.mapper.CourseMapper;
import com.example.studentApi.repository.CourseRepository;
import com.example.studentApi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository repository;

    public CourseService(CourseMapper courseMapper, CourseRepository courseRepository, StudentRepository repository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.repository = repository;
    }
    public CourseResponseDto addCourse(Long student_Id, CourseRequestDto dto){
        var student = repository.findById(student_Id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
        Course course = courseMapper.toEntity(dto);
        course.setStudent(student);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toResponse(savedCourse);
    }
    public List<CourseResponseDto> getCourse(Long id){
       var student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
       return student.getCourses()
               .stream()
               .map(courseMapper::toResponse)
               .toList();
    }

}
