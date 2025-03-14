package com.jiyeon.portfolio.domain.repository

import com.jiyeon.portfolio.domain.entity.Experience
import com.jiyeon.portfolio.domain.entity.ExperienceDetail
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

private val logger = KotlinLogging.logger { }

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceRepositoryTest(
    @Autowired val experienceRepository: ExperienceRepository) {

    val DATA_SIZE = 5

    // 더미 객체 생성
    private fun createExperience(n: Int): Experience {
        val experience = Experience(
            title = "${n}",
            description = "테스트 설명 {n}",
            startYear = 2025,
            startMonth = 3,
            endYear = 2025,
            endMonth = 3,
            isActive = true)

        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) {
            val experienceDetail = ExperienceDetail(content = "테스트 ${i}", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)

        return experience
    }

    @BeforeAll
    fun beforeAll() {
        logger.info { "데이터 초기화 이전 조회 시작" }
        val beforeInitialize = experienceRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        logger.info { "데이터 초기화 이전 조회 종료" }

        logger.info { "테스트 데이터 초기화 시작" }
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE) {
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        logger.info { "테스트 데이터 초기화 종료" }
    }

    @Test
    fun testFindAll() {
        logger.info { "findAll 테스트 시작" }
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)
        logger.info { "experiences.size: ${experiences.size}" }

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            logger.info { "experience.details.size: ${experience.details.size}" }
        }
        logger.info { "findAll 테스트 종료" }
    }

    @Test
    fun testFindAllByIsActive() {
        logger.info { "findAllByIsActive 테스트 시작" }
        val experiences = experienceRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)
        logger.info { "experiences.size: ${experiences.size}" }

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            logger.info { "experience.details.size: ${experience.details.size}" }
        }
        logger.info { "findAllByIsActive 테스트 종료" }
    }
}