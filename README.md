# 🛒 Commerce project

## 📚 Overview

---

commerce 프로젝트는 전자상거래 플랫폼을 구축하기 위한 자바 기반의 웹 애플리케이션입니다. 이 프로젝트는 멀티 모듈로 이루어져 있으며, 회원가입, 주문 처리, 예약 상품구매 등 전자상거래의 핵심 기능을 포함하고
있습니다.

<br>

## 🛠️ Tech Stack

---

![spring boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![spring security](https://img.shields.io/badge/-Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![spring cloud](https://img.shields.io/badge/-Spring%20Cloud-6DB33F?style=for-the-badge&logo=icloud&logoColor=white)
![mysql](https://img.shields.io/badge/-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![redis](https://img.shields.io/badge/-Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![redis](https://img.shields.io/badge/-Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

- **Language**: Java 21
- **Framework**: Spring boot 3.2.4
- **Build**: Gradle 8.7
- **Database**: MySQL 8.3
- **Container**: Docker

<br>

## ✨ Feature

---

- Spring Security 및 JWT를 활용한 사용자 인증 및 인가
- Google SMTP를 활용한 사용자 e-mail 인증
- Spring Cloud를 활용한 마이크로서비스 아키텍처
- 지정된 시간에만 구매 가능한 예약 구매 기능

<br>

## 🏃‍♂️‍➡️ Run

---

### 로컬 실행

1. 저장소 클론:
    ```sh
    git clone https://github.com/jinu0729/commerce.git
    cd commerce
    ```
2. Gradle 빌드:
    ```sh
    ./gradlew build
    ```
3. 애플리케이션 실행:
    ```sh
    ./gradlew bootRun
    ```

<br>

### Docker를 이용한 실행

1. Docker Compose 실행:
    ```sh
    docker-compose up -d
    ```

<br>

## 📑 Docs

---

https://commerce.gitbook.io/docs/

