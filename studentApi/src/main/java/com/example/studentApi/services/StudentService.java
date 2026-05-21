package com.example.studentApi.services;

import com.example.studentApi.exception.StudentNotFoundException;
import com.example.studentApi.dto.StudentRequestDto;
import com.example.studentApi.dto.StudentResponseDto;
import com.example.studentApi.entity.Student;
import com.example.studentApi.mapper.StudentMapper;
import com.example.studentApi.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    private final StudentMapper mapper;
    private final StudentRepository repository;

    public StudentService(StudentMapper mapper, StudentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public StudentResponseDto createStudent( StudentRequestDto dto){
        var student = mapper.toEntity(dto);
        Student savedStudent = repository.save(student);
        return mapper.toResponse(savedStudent);
    }
    public StudentResponseDto findByStudentId(Long id){
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(()-> new StudentNotFoundException("Student not found"));
    }
    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto){
        Student student = repository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not found"));
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setAge(dto.age());
        Student savedStudent = repository.save(student);
        return mapper.toResponse(savedStudent);
    }
    public void deleteStudentById(Long id){
        var student = repository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student not found"));
        repository.delete(student);
    }

    public List<StudentResponseDto> findStudentsByPrefix(String prefix){
        return repository.findByNameStartingWith(prefix)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public List<StudentResponseDto> findByNameAndAge(String prefix, int age){
        return repository.findByNameStartingWithAndAgeGreaterThan(prefix, age)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    public Page<StudentResponseDto> studentPage(int page, int size) {
        Page<Student> students = repository.findAll(PageRequest.of(page, size));
        return students
                .map(mapper::toResponse);

    }
    public Page<StudentResponseDto> sortPage(int page, int size, String sortBy){
        return repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy)))
                .map(mapper::toResponse);
    }
}
