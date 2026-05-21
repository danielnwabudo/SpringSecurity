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

    @PostMapping("/{student_id}/courses")
    public ResponseEntity<CourseResponseDto> addingStudent(@PathVariable Long student_id,@Valid @RequestBody CourseRequestDto dto){
        return ResponseEntity.ok(service.addCourse(student_id, dto));
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseResponseDto>> getCourses(@PathVariable Long id){
        return ResponseEntity.ok(service.getCourse(id));
    }
}
