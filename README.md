## 🛒 Commerce project

commerce 프로젝트는 전자상거래 플랫폼을 구축하기 위한 자바 기반의 웹 애플리케이션입니다. 이 프로젝트는 멀티 모듈로 이루어져 있으며, 회원가입, 주문 처리, 예약 상품구매 등 전자상거래의 핵심 기능을 포함하고
있습니다.

<br>

## 🏃‍♂ Run

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

## 🛠️ Tech Stack

![spring boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![spring cloud](https://img.shields.io/badge/-Spring%20Cloud-6DB33F?style=for-the-badge&logo=icloud&logoColor=white)
![spring security](https://img.shields.io/badge/-Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
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

- Spring Security 및 JWT를 활용한 사용자 인증 및 인가
- Google SMTP를 활용한 사용자 e-mail 인증
- 지정된 시간에만 구매 가능한 예약 구매 기능
- Spring Cloud 및 Netflix Eureka를 활용한 마이크로서비스 아키텍처
- Redis를 이용한 동시성 처리

<br>

## 🏗 Architecture

![Architecture](https://github.com/jinu0729/commerce/assets/108972941/d963ce0e-cf67-441f-8c64-28fb4ba9b9d2)

<br>

## 🗓️ ERD

![ERD](https://github.com/jinu0729/commerce/assets/108972941/260f9765-6f2a-4c4c-ae9c-a33fe1ae461a)

<br>

## 📁 Folder Structure

``` sh
├── README.md
├── build
│   └── resolvedMainClassName
├── build.gradle
├── commerce-api-gateway
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commerceapigateway
│       │   │               ├── CommerceApiGatewayApplication.java
│       │   │               ├── filter
│       │   │               │   └── AuthorizationHeaderFilter.java
│       │   │               └── util
│       │   │                   └── JwtUtil.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           └── resources
├── commerce-auth-service
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commerceauthservice
│       │   │               ├── CommerceAuthServiceApplication.java
│       │   │               ├── domain
│       │   │               │   ├── client
│       │   │               │   │   └── UserServiceClient.java
│       │   │               │   ├── controller
│       │   │               │   │   └── AuthController.java
│       │   │               │   ├── dto
│       │   │               │   │   ├── RefreshTokenRequestDto.java
│       │   │               │   │   ├── SignInRequestDto.java
│       │   │               │   │   └── UserDto.java
│       │   │               │   ├── entity
│       │   │               │   │   └── Auth.java
│       │   │               │   ├── repository
│       │   │               │   │   └── AuthRepository.java
│       │   │               │   └── service
│       │   │               │       ├── AuthService.java
│       │   │               │       └── AuthServiceImpl.java
│       │   │               └── global
│       │   │                   ├── config
│       │   │                   │   └── PasswordConfig.java
│       │   │                   ├── cookie
│       │   │                   │   └── CookieUtil.java
│       │   │                   ├── jwt
│       │   │                   │   ├── JwtAuthenticationFilter.java
│       │   │                   │   ├── JwtAuthorizationFilter.java
│       │   │                   │   └── JwtUtil.java
│       │   │                   ├── security
│       │   │                   │   ├── SecurityConfig.java
│       │   │                   │   ├── UserDetailsImpl.java
│       │   │                   │   └── UserDetailsServiceImpl.java
│       │   │                   └── util
│       │   │                       └── Timestamped.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           └── resources
├── commerce-common
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commercecommon
│       │   │               ├── dto
│       │   │               │   └── ResponseBodyDto.java
│       │   │               ├── exception
│       │   │               │   ├── CustomException.java
│       │   │               │   ├── ErrorCode.java
│       │   │               │   ├── ExceptionResponse.java
│       │   │               │   └── GlobalExceptionHandler.java
│       │   │               └── redis
│       │   │                   ├── RedisConfig.java
│       │   │                   └── RedisService.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           └── resources
├── commerce-discovery-service
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commercediscoveryservice
│       │   │               └── CommerceDiscoveryServiceApplication.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           └── resources
├── commerce-order-service
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commerceorderservice
│       │   │               ├── CommerceOrderServiceApplication.java
│       │   │               ├── domain
│       │   │               │   ├── cart
│       │   │               │   │   ├── controller
│       │   │               │   │   │   └── CartController.java
│       │   │               │   │   ├── dto
│       │   │               │   │   │   ├── request
│       │   │               │   │   │   │   ├── CartItemEditRequestDto.java
│       │   │               │   │   │   │   └── CartRequestDto.java
│       │   │               │   │   │   └── response
│       │   │               │   │   │       └── CartItemResponseDto.java
│       │   │               │   │   ├── entity
│       │   │               │   │   │   ├── Cart.java
│       │   │               │   │   │   └── CartItem.java
│       │   │               │   │   ├── repository
│       │   │               │   │   │   ├── CartDetailRepository.java
│       │   │               │   │   │   └── CartRepository.java
│       │   │               │   │   └── service
│       │   │               │   │       ├── CartDetailService.java
│       │   │               │   │       ├── CartDetailServiceImpl.java
│       │   │               │   │       ├── CartService.java
│       │   │               │   │       └── CartServiceImpl.java
│       │   │               │   └── order
│       │   │               │       ├── controller
│       │   │               │       │   └── OrderController.java
│       │   │               │       ├── dto
│       │   │               │       │   ├── request
│       │   │               │       │   │   └── OrderRequestDto.java
│       │   │               │       │   └── response
│       │   │               │       │       ├── OrderDetailResponseDto.java
│       │   │               │       │       └── OrderResponseDto.java
│       │   │               │       ├── entity
│       │   │               │       │   ├── Order.java
│       │   │               │       │   ├── OrderDetail.java
│       │   │               │       │   └── Status.java
│       │   │               │       ├── repository
│       │   │               │       │   ├── OrderDetailRepository.java
│       │   │               │       │   └── OrderRepository.java
│       │   │               │       ├── scheduler
│       │   │               │       │   └── OrderScheduler.java
│       │   │               │       └── service
│       │   │               │           ├── OrderDetailService.java
│       │   │               │           ├── OrderDetailServiceImpl.java
│       │   │               │           ├── OrderService.java
│       │   │               │           └── OrderServiceImpl.java
│       │   │               └── global
│       │   │                   ├── client
│       │   │                   │   ├── ProductServiceClient.java
│       │   │                   │   └── dto
│       │   │                   │       └── ProductResponseDto.java
│       │   │                   ├── cookie
│       │   │                   │   └── CookieUtil.java
│       │   │                   ├── jwt
│       │   │                   │   └── JwtUtil.java
│       │   │                   └── util
│       │   │                       ├── OrderUtil.java
│       │   │                       └── Timestamped.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           └── resources
├── commerce-product-service
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commerceproductservice
│       │   │               ├── CommerceProductServiceApplication.java
│       │   │               ├── domain
│       │   │               │   ├── controller
│       │   │               │   │   └── ProductController.java
│       │   │               │   ├── dto
│       │   │               │   │   └── ProductResponseDto.java
│       │   │               │   ├── entity
│       │   │               │   │   ├── Product.java
│       │   │               │   │   └── Type.java
│       │   │               │   ├── repository
│       │   │               │   │   └── ProductRepository.java
│       │   │               │   └── service
│       │   │               │       ├── ProductService.java
│       │   │               │       └── ProductServiceImpl.java
│       │   │               └── global
│       │   │                   ├── dto
│       │   │                   │   └── ResponseBodyDto.java
│       │   │                   ├── exception
│       │   │                   │   ├── CustomException.java
│       │   │                   │   ├── ErrorCode.java
│       │   │                   │   ├── ExceptionResponse.java
│       │   │                   │   └── GlobalExceptionHandler.java
│       │   │                   ├── redis
│       │   │                   │   ├── AopForTransaction.java
│       │   │                   │   ├── CustomSpringELParser.java
│       │   │                   │   ├── DistributedLock.java
│       │   │                   │   ├── DistributedLockAop.java
│       │   │                   │   └── RedissonConfig.java
│       │   │                   └── util
│       │   │                       └── Timestamped.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           │   └── com
│           │       └── jinu
│           │           └── commerceproductservice
│           │               └── domain
│           │                   └── service
│           │                       └── ProductServiceTest.java
│           └── resources
├── commerce-user-service
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── generated
│       │   ├── java
│       │   │   └── com
│       │   │       └── jinu
│       │   │           └── commerceuserservice
│       │   │               ├── CommerceUserService.java
│       │   │               ├── domain
│       │   │               │   ├── controller
│       │   │               │   │   └── UserController.java
│       │   │               │   ├── dto
│       │   │               │   │   ├── EmailRequestDto.java
│       │   │               │   │   ├── SignUpRequestDto.java
│       │   │               │   │   ├── UpdateInfoRequestDto.java
│       │   │               │   │   └── UpdatePasswordRequestDto.java
│       │   │               │   ├── entity
│       │   │               │   │   └── User.java
│       │   │               │   ├── repository
│       │   │               │   │   └── UserRepository.java
│       │   │               │   ├── service
│       │   │               │   │   ├── UserService.java
│       │   │               │   │   └── UserServiceImpl.java
│       │   │               │   └── util
│       │   │               │       ├── Timestamped.java
│       │   │               │       └── UserUtil.java
│       │   │               └── global
│       │   │                   ├── config
│       │   │                   │   └── PasswordConfig.java
│       │   │                   ├── cookie
│       │   │                   │   └── CookieUtil.java
│       │   │                   ├── email
│       │   │                   │   ├── config
│       │   │                   │   │   └── EmailConfig.java
│       │   │                   │   └── service
│       │   │                   │       ├── EmailService.java
│       │   │                   │       └── EmailServiceImpl.java
│       │   │                   └── jwt
│       │   │                       └── JwtUtil.java
│       │   └── resources
│       │       └── application.yaml
│       └── test
│           ├── java
│           └── resources
├── docker-compose.yaml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

<br>

## 📑 API

https://commerce.gitbook.io/api

<br>

## 🤔 Think About

https://commerce.gitbook.io/think-about