package com.example.studentApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: Add @Column(nullable = false) to enforce the constraint at the DB level too.
    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "student_id")
    // TODO: @JsonBackReference / @JsonManagedReference are useful for avoiding serialisation cycles,
    // but consider using DTOs for serialisation instead of exposing entities directly via Jackson.
    // This keeps your API contract decoupled from your database model.
    private Student student;
}
