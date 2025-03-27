package com.jiyeon.portfolio.presentation.dto

import com.jiyeon.portfolio.domain.entity.Introduction

data class IntroductionDTO(val content: String) {

    constructor(introduction: Introduction) : this(content = introduction.content)
}