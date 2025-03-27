package com.jiyeon.portfolio.domain.repository

import com.jiyeon.portfolio.domain.constant.SkillType
import com.jiyeon.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SkillRepository : JpaRepository<Skill, Long> {

    fun findAllByIsActive(isActive: Boolean): List<Skill>

    fun findByNameIgnoreCaseAndType(name: String, type: SkillType): Optional<Skill>
}