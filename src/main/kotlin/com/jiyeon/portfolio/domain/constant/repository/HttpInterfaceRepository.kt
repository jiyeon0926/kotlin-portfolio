package com.jiyeon.portfolio.domain.constant.repository

import com.jiyeon.portfolio.domain.constant.entity.HttpInterface
import org.springframework.data.jpa.repository.JpaRepository

interface HttpInterfaceRepository : JpaRepository<HttpInterface, Long> {
}