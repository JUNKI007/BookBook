package com.example.brunchStory.member.repository;

import com.example.brunchStory.member.domain.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
