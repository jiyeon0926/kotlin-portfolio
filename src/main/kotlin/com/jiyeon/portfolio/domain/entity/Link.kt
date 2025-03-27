package com.jiyeon.portfolio.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Link(name: String, content: String, isActive: Boolean) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var content: String = content

    var isActive: Boolean = isActive
}