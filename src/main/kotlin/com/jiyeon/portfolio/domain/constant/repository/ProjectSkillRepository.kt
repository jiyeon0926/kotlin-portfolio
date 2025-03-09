package com.jiyeon.portfolio.domain.constant.repository

import com.jiyeon.portfolio.domain.constant.entity.ProjectSkill
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectSkillRepository : JpaRepository<ProjectSkill, Long> {
}