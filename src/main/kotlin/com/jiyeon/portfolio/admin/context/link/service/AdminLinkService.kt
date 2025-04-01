package com.jiyeon.portfolio.admin.context.link.service

import com.jiyeon.portfolio.admin.context.form.LinkForm
import com.jiyeon.portfolio.admin.data.TableDTO
import com.jiyeon.portfolio.domain.entity.Link
import com.jiyeon.portfolio.domain.repository.LinkRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminLinkService(private val linkRepository: LinkRepository) {

    fun getLinkTable(): TableDTO {
        val classInfo = Link::class
        val entities = linkRepository.findAll()

        return TableDTO.from(classInfo, entities)
    }

    @Transactional
    fun save(form: LinkForm) {
        val link = form.toEntity()
        linkRepository.save(link)
    }

    @Transactional
    fun update(id: Long, form: LinkForm) {
        val link = form.toEntity(id)
        linkRepository.save(link)
    }
}