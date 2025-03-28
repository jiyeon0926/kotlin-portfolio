# 배포 방법 순서 정리

## gitignore
docker-compose.yml 파일 추가
```
docker-compose.yml
```

## docker-compose.yml
- docker-compose.yml 파일 생성
- 3306 포트 번호는 이미 사용 중이어서 3307로 설정
- services 실행
```
version: '2'

services:
  mysql:
    image: mysql
    container_name: mysql
    ports:
      - "3307:3306"
    environment:
      - "MYSQL_ROOT_PASSWORD=비밀번호 설정"
      - "TZ=Asia/Seoul"
      - "LC_ALL=C.UTF-8"
    command:
      - --character-set-server=utf8mb4
    volumes:
      - /var/lib/docker/volumes/mysql/_data:/var/lib/mysql
```
```
> docker ps
CONTAINER ID   IMAGE     COMMAND                   CREATED         STATUS         PORTS                               NAMES
[CONTAINER ID]   mysql     "docker-entrypoint.s…"   4 seconds ago   Up 3 seconds   33060/tcp, 0.0.0.0:3307->3306/tcp   mysql
```

## DBeaver
- localhost
- 3307
- 설정한 비밀번호
- 데이터베이스 연결 후, 테이블 생성
```
use kotlin_portfolio;

 create table account(
 account_id int not null auto_increment
 , login_id varchar(20)
 , password varchar(255)
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(account_id)
 );

 insert into account(login_id, password, created_date_time, updated_date_time)
 values ('admin1', '$2a$10$BWi6SLqZRJyVvJyufjTtHeYXNNhpNY9rxaVl9fBOE.1t3QF98B.cO', now(), now());

create table http_interface(
 http_interface_id int not null auto_increment
 , cookies varchar(255)
 , referer varchar(255)
 , local_addr varchar(255)
 , remote_addr varchar(255)
 , remote_host varchar(255)
 , request_uri varchar(255)
 , user_agent varchar(255)
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(http_interface_id)
 );

 create table achievement(
  achievement_id int not null auto_increment
 , created_date_time datetime
, title varchar(255)
 , description varchar(255)
 , achieved_date date
 , host varchar(255)
 , is_active bit
 , updated_date_time datetime
 , primary key(achievement_id)
 );

 create table introduction(
  introduction_id int not null auto_increment
 , content varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(introduction_id)
 );

 create table link(
  link_id int not null auto_increment
 , name varchar(255)
 , content varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(link_id)
 );

 create table skill(
  skill_id int not null auto_increment
 , name varchar(255)
 , skill_type varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(skill_id)
 );

 create table experience(
  experience_id int not null auto_increment
 , title varchar(255)
 , description varchar(255)
 , start_year smallint
 , start_month tinyint
 , end_year smallint
 , end_month tinyint
 , is_active bit
, created_date_time datetime
 , updated_date_time datetime
 , primary key(experience_id)
 );

 create table experience_detail(
  experience_detail_id int not null auto_increment
 , experience_id int
 , content varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(experience_detail_id)
 , foreign key(experience_id) references experience(experience_id)
 );

 create table project(
  project_id int not null auto_increment
 , name varchar(255)
 , description varchar(255)
 , start_year smallint
 , start_month tinyint
 , end_year smallint
 , end_month tinyint
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(project_id)
 );

 create table project_detail(
  project_detail_id int not null auto_increment
 , project_id int
 , content varchar(255)
 , url varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(project_detail_id)
 , foreign key(project_id) references project(project_id)
 );

 create table project_skill(
  project_skill_id int not null auto_increment
 , project_id int
 , skill_id int
 , created_date_time datetime
  , updated_date_time datetime
  , primary key(project_skill_id)
  , foreign key(project_id) references project(project_id)
  , foreign key(skill_id) references skill(skill_id)
 );
```

## Dockerfile
먼저, jar 파일이 있는지 확인하고 Dockerfile을 실행한다
```
dir build\libs\
```
```
FROM openjdk:17

LABEL maintainer="yeonji20250126@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/portfolio-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} portfolio-jiyeon.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/portfolio-jiyeon.jar"]
```

![image](https://github.com/user-attachments/assets/01dd2654-e11f-4373-88a9-375e952b4f1f)
![image](https://github.com/user-attachments/assets/87f65a8c-ae96-4367-bd7b-00c21cd7ed61)
![image](https://github.com/user-attachments/assets/e8eac88b-c4a6-4877-ab59-d2035ac1b6ec)
![image](https://github.com/user-attachments/assets/8caaf2a2-d4d3-4aa3-9c92-a9a9b8398b84)

이미지 태그 지정 후, Dockerfile을 다시 build 한다
오류가 생길 경우, build.gradle 파일의 아래 코드를 주석 처리하면 해결된다
```
tasks.withType<Test> {
	useJUnitPlatform()
}
```

## docker-compose.yml 내용 추가
```
...
  portfolio-jiyeon:
    image: jiyeon0126/portfolio-jiyeon
    container_name: portfolio-jiyeon
    ports:
      - "8080:8080"
    environment:
      - "SPRING_PROFILES_ACTIVE=docker"
      - "jasypt.encryptor.key=q1w2e3"
    volumes:
      - /var/lib/docker/volumes/portfolio-jiyeon/_data:/tmp
    depends_on:
      - mysql
```

## jasypt
- jasypt 의존성을 추가
- asyptConfiguration 클래스를 생성
- 테스트 코드를 작성해 암호화된 문자를 application-docker.yml 파일의 password에 추가
```
implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
```
```
@SpringBootTest
class PortfolioApplicationTests {
...
        @Test
	fun jasypt() {
		val plainText = "dkssudgktpdy"
		val encryptor = PooledPBEStringEncryptor()
		val config = SimpleStringPBEConfig()

		config.password = "qwer1234"
		config.algorithm = "PBEWithMD5AndDES"
		config.setKeyObtentionIterations("1000")
		config.setPoolSize("1")
		config.providerName = "SunJCE"
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
		config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator")
		config.stringOutputType = "base64"

		encryptor.setConfig(config)

		val encryptedText: String = encryptor.encrypt(plainText)
		val decryptedText: String = encryptor.decrypt(encryptedText)
		println(encryptedText)
		println(decryptedText)
	}
}
```

## docker-compose.yml 재실행
```
[+] Running 2/2
 ✔ Container mysql             Running                                                            0.0s 
 ✔ Container portfolio-jiyeon  Started  
```
