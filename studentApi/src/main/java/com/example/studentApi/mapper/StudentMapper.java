package com.example.studentApi.mapper;

import com.example.studentApi.dto.StudentRequestDto;
import com.example.studentApi.dto.StudentResponseDto;
import com.example.studentApi.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto dto){
        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setAge(dto.age());
        return student;
    }
    public StudentResponseDto toResponse(Student student){
        return new StudentResponseDto(student.getId(), student.getName(),student.getEmail());
    }
}
