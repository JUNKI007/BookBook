package com.example.bookBook.member.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "writer_apply")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
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
