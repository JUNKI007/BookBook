package com.example.brunchStory.member.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "writer_apply")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WriterApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private LocalDate localDate;

    @OneToOne
    Member applicant;
}
