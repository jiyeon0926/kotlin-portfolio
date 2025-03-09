package com.jiyeon.portfolio.domain.repository

import com.jiyeon.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long> {
}