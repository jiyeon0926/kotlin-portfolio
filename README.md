# 배포 방법 정리

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
