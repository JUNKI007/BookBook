package com.example.brunchStory.member.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "subscribes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subscribe_time")
    private LocalDate subscribeTime; //구독 시간 정보 추가

    @ManyToOne
    private Member author;

    @ManyToOne
    private Member member;

}
