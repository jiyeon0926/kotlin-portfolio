package com.jiyeon.portfolio.admin.context.link.controller

import com.jiyeon.portfolio.admin.context.form.LinkForm
import com.jiyeon.portfolio.admin.context.link.service.AdminLinkService
import com.jiyeon.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/links")
class AdminLinkApiController(private val adminLinkService: AdminLinkService) {

    @PostMapping
    fun postLink(@RequestBody @Validated form: LinkForm): ResponseEntity<Any> {
        adminLinkService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putLink(@PathVariable id: Long,
                @RequestBody form: LinkForm): ResponseEntity<Any> {
        adminLinkService.update(id, form)

        return ApiResponse.successUpdate()
    }
}