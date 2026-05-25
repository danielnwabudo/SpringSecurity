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

    // TODO: Was mapped to /students/studentsApi — keep paths consistent and RESTful (e.g. POST /students).
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto responseDto = service.createStudent(dto);
        return ResponseEntity.status(201).body(responseDto);
    }

    // TODO: Was mapped to /students/students/{id} — double "students" in the URL is a mistake.
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByStudentId(id));
    }

    // TODO: @Valid was missing — request body was not validated on update.
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto dto) {
        return ResponseEntity.ok(service.updateStudent(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> findStudents(@RequestParam String prefix) {
        return ResponseEntity.ok(service.findStudentsByPrefix(prefix));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentResponseDto>> findStudentsByNameAndAge(@RequestParam String prefix,
                                                                              @RequestParam int age) {
        return ResponseEntity.ok(service.findByNameAndAge(prefix, age));
    }

    // TODO: Was mapped to /students/getStudent — prefer /students/page for clarity.
    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponseDto>> getStudentsPage(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.studentPage(page, size));
    }

    // TODO: GET /students with query params is actually a reasonable REST pattern for paginated/sorted listing.
    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> getSortedPage(@RequestParam int page,
                                                                   @RequestParam int size,
                                                                   @RequestParam String sortBy) {
        return ResponseEntity.ok(service.sortPage(page, size, sortBy));
    }
}
