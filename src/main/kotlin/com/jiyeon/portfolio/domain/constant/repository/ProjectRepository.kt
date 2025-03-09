package com.jiyeon.portfolio.domain.constant.repository

import com.jiyeon.portfolio.domain.constant.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long> {
}