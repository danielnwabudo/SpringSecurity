package com.example.studentApi.controller;

import com.example.studentApi.dto.CourseRequestDto;
import com.example.studentApi.dto.CourseResponseDto;
import com.example.studentApi.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // TODO: Method was named "addingStudent" but it adds a course — name methods after what they do.
    // TODO: Path variable snake_case (student_id) should be camelCase (studentId) per Java conventions.
    @PostMapping("/{studentId}/courses")
    public ResponseEntity<CourseResponseDto> addCourse(@PathVariable Long studentId, @Valid @RequestBody CourseRequestDto dto) {
        return ResponseEntity.ok(service.addCourse(studentId, dto));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseResponseDto>> getCourses(@PathVariable Long id){
        return ResponseEntity.ok(service.getCourse(id));
    }
}
