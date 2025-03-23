package com.jiyeon.portfolio.presentation.dto

import com.jiyeon.portfolio.domain.entity.ProjectDetail

class ProjectDetailDTO(
    val content: String,
    val url: String?) {

    constructor(projectDetail: ProjectDetail) : this(
        content = projectDetail.content,
        url = projectDetail.url
    )
}