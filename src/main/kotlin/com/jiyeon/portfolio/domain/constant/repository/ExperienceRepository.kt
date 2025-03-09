package com.jiyeon.portfolio.domain.constant.repository

import com.jiyeon.portfolio.domain.constant.entity.Experience
import org.springframework.data.jpa.repository.JpaRepository

interface ExperienceRepository : JpaRepository<Experience, Long> {
}