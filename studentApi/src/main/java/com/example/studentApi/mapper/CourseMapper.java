package com.example.studentApi.mapper;

import com.example.studentApi.dto.CourseRequestDto;
import com.example.studentApi.dto.CourseResponseDto;
import com.example.studentApi.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toEntity(CourseRequestDto dto){
        Course course = new Course();
        course.setTitle(dto.title());
        return course;
    }

    public CourseResponseDto toResponse(Course course){
        return new CourseResponseDto(course.getId(), course.getTitle());
    }
}
