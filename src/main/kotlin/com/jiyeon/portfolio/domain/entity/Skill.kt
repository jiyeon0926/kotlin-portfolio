package com.jiyeon.portfolio.domain.entity

import com.jiyeon.portfolio.domain.constant.SkillType
import jakarta.persistence.*

@Entity
class Skill(name: String, type: String, isActive: Boolean) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    @Column(name = "skill_type")
    @Enumerated(value = EnumType.STRING)
    var type: SkillType = SkillType.valueOf(type)

    var isActive: Boolean = isActive
}