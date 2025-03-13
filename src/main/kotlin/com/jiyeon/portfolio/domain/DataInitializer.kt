package com.jiyeon.portfolio.domain

import com.jiyeon.portfolio.domain.constant.SkillType
import com.jiyeon.portfolio.domain.entity.*
import com.jiyeon.portfolio.domain.repository.*
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

private val logger = KotlinLogging.logger { }

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository
) {

    @PostConstruct
    fun initializeData() {
        logger.info { "테스트 데이터 초기화" }

        // Achievement
        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "사무자동화산업기사",
                description = "사무자동화시스템, 사무경영관리개론, 프로그래밍, 정보통신개론",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2022, 9, 2),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        // Introduction
        val introductions = mutableListOf<Introduction>(
            Introduction(content = "프로젝트나 코딩 테스트 문제를 해결하며 겪은 이슈를 Trouble Shooting으로 정리하여 되돌아봅니다.", isActive = true),
            Introduction(content = "앞에서 이끌기보다는 뒤에서 받쳐주는 스타일이지만, 필요할 때는 주도적으로 이끄는 것도 마다하지 않습니다.", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        // Link
        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/jiyeon0926", isActive = true),
            Link(name = "Study Blog", content = "https://blog.naver.com/yeondata", isActive = true),
            Link(name = "Trouble Shooting", content = "https://velog.io/@yeoni9094/posts", isActive = true),
        )
        linkRepository.saveAll(links)

        // Experience
        val experience1 = Experience(
            title = "내일배움캠프 스프링 백엔드",
            description = "Spring 7기",
            startYear = 2024,
            startMonth = 9,
            endYear = 2025,
            endMonth = 2,
            isActive = true,
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "Spring 프레임워크 기반 웹 애플리케이션 개발 과정 학습", isActive = true),
                ExperienceDetail(content = "팀 프로젝트 5회 진행", isActive = true)
            )
        )

        val experience2 = Experience(
            title = "인프런 워밍업 클럽 3기 BE 스터디",
            description = "Spring Boot with Kotlin",
            startYear = 2025,
            startMonth = 3,
            endYear = null,
            endMonth = null,
            isActive = true,
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "나만의 포트폴리오 사이트 만들기 프로젝트", isActive = true),
                ExperienceDetail(content = "학사 관리 서비스 미니 프로젝트", isActive = true)
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        // Skill
        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val mssql = Skill(name = "MSSQL", type = SkillType.DATABASE.name, isActive = true)
        val redis = Skill(name = "Redis", type = SkillType.DATABASE.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, kotlin, spring, mysql, mssql, redis))

        // Project
        val project1 = Project(
            name = "Travel Shooting",
            description = "포스터를 작성하여 다른 사용자들에게 실질적인 여행 정보를 제공하고, 유용한 여행지를 추천하는 서비스",
            startYear = 2025,
            startMonth = 1,
            endYear = 2025,
            endMonth = 2,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "Kakao MAP API를 활용한 장소 검색 기능 구현", url = null, isActive = true),
                ProjectDetail(content = "Redis 분산락을 적용해 예약 동시성 이슈 해결", url = null, isActive = true),
                ProjectDetail(content = "Kakao Pay API를 활용한 결제 기능 구현", url = null, isActive = true),
                ProjectDetail(content = "메일 전송 및 알림 내역 기능 구현", url = null, isActive = true),
                ProjectDetail(
                    content = "쓰레드 풀을 적용해 예약 성능을 10초 → 1초로 개선",
                    url = "https://github.com/jiyeon0926/travel-shooting/wiki/%EC%98%88%EC%95%BD-%EC%84%B1%EB%8A%A5-%EA%B0%9C%EC%84%A0",
                    isActive = true
                ),
                ProjectDetail(
                    content = "Github Repository",
                    url = "https://github.com/jiyeon0926/travel-shooting",
                    isActive = true
                )
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql),
                ProjectSkill(project = project1, skill = redis)
            )
        )

        val project2 = Project(
            name = "학사 관리 서비스",
            description = "학생들이 전공 및 교양 과목을 수강 신청할 수 있는 서비스",
            startYear = 2025,
            startMonth = 3,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        project2.addDetails(
            mutableListOf(
                ProjectDetail(content = "학생, 교수, 관리자 권한에 따른 기능 구현", url = null, isActive = true),
                ProjectDetail(
                    content = "Github Repository",
                    url = "https://github.com/jiyeon0926/academic-management",
                    isActive = true
                )
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = kotlin),
                ProjectSkill(project = project2, skill = spring),
                ProjectSkill(project = project2, skill = mysql)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }
}