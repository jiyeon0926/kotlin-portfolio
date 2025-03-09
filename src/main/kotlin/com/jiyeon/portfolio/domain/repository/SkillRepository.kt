package com.jiyeon.portfolio.domain.repository

import com.jiyeon.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long> {
}