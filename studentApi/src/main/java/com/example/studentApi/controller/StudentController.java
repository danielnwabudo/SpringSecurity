package com.example.studentApi.controller;

import com.example.studentApi.dto.StudentRequestDto;
import com.example.studentApi.dto.StudentResponseDto;
import com.example.studentApi.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/studentsApi")
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto dto){
        StudentResponseDto responseDto = service.createStudent(dto);
        return  ResponseEntity.status(201).body(responseDto);
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto findBy_Id(@PathVariable Long id){
        return service.findByStudentId(id);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<StudentResponseDto> updatingStudent(@PathVariable Long id, @RequestBody StudentRequestDto dto){
        return ResponseEntity.ok(service.updateStudent(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> findStudents(@RequestParam String prefix){
        return ResponseEntity.ok(service.findStudentsByPrefix(prefix));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentResponseDto>> findStudentsNameAndAge(@RequestParam String prefix,
                                                                           @RequestParam int age){
        return ResponseEntity.ok(service.findByNameAndAge(prefix, age));
    }
    @GetMapping("/getStudent")
    public ResponseEntity<Page<StudentResponseDto>> pageToDto(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(service.studentPage(page, size ));
    }

    @GetMapping("")
    public ResponseEntity<Page<StudentResponseDto>> sortingPage(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy){
        return ResponseEntity.ok(service.sortPage(page, size, sortBy ));
    }
}
