package com.jiyeon.portfolio.domain.constant.repository

import com.jiyeon.portfolio.domain.constant.entity.Achievement
import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository : JpaRepository<Achievement, Long> {
}