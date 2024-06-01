package com.api.core.domain.dopamineTime.repository;

import com.api.core.domain.dopamineTime.entity.DopamineTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DopamineTimeRepository extends JpaRepository<DopamineTime, Long> {
}