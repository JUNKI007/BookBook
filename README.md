# Book Book
***
사용자가 작성한 게시글을 모아 책으로 출판할 수 있는 BrunchStory 스타일의 웹 서비스
***
&nbsp;
#  📗 개발 동기
단순한 CRUD 개발이 아닌, 실제 업무에서 자주 사용되는 '데이터 간의 관계'에 집중하고 싶었습니다.

게시글을 모아 책을 만든다면 게시글과 책은 N:1 관계, 책과 게시글은 각각 한 명의 작성자를 가지므로 1:1 관계, 책과 게시글은 여러 개의 댓글을 가질 수 있어 1:N 관계, 하나의 게시글이 여러 주제를 가질 수 있으므로 N:N 관계 등 다양한 데이터 모델링을 구현할 수 있는 프로젝트라고 생각했습니다.

이러한 복잡한 관계를 직접 설계하고 구현하면서 데이터베이스 모델링과 비즈니스 로직을 깊이 이해하고자 했습니다.





&nbsp;
***
&nbsp;

# 🎥 영상
[![프로젝트 영상](https://img.youtube.com/vi/7pFIFMa0F4Y/0.jpg)](https://www.youtube.com/watch?v=7pFIFMa0F4Y)


&nbsp;
***
# 👩‍🦲 팀원소개


1. 정준기 : https://github.com/JUNKI007
2. 이태열 : https://github.com/gotoAbraxas
3. 최웅진 : https://github.com/1996svibe
4. 최영준 : https://github.com/choiy6




&nbsp;
***
&nbsp;
# 🛠️ 기술 스택


### **Backend**
![Java](https://img.shields.io/badge/Java17-FF7700?style=flat) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=springboot&logoColor=white)  ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat&logo=spring&logoColor=white) ![Spring MVC](https://img.shields.io/badge/Spring%20MVC-6DB33F?style=flat&logo=spring&logoColor=white) ![QuartzScheduler](https://img.shields.io/badge/Quartz%20Scheduler-5530FF?style=flat&logo=&logoColor=white) 

![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=hibernate&logoColor=white) ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white) ![Lombok](https://img.shields.io/badge/Lombok-0078D3?style=flat) ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white) ![RESTful API](https://img.shields.io/badge/RESTfull%20API-A100FF?style=flat&logo=&logoColor=white) 

### **DevOps & Tools**
![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white) ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=flat&logo=intellij-idea&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=postman&logoColor=black)

&nbsp;
***

&nbsp;
# 📂 레포지터리
- [GitHub](https://github.com/JUNKI007/BookBook)

&nbsp;
 
***
&nbsp;
# **💾  프로젝트 주요 기능**
&nbsp;


#### 1️⃣ 회원 기능

- **회원 가입 (`POST /api/v1/members/signup`)**: 계정 생성  
- **로그인 (`POST /api/v1/members/login`)**: JWT 로그인 인증  
- **회원 역할(Role) 관리**: Role 필드를 통해 일반회원, 작가, 관리자의 권한을 달리함

&nbsp;

---

#### 2️⃣ 게시글 기능

- **게시글 작성 (`POST /api/posts`)**: 게시글 작성  
- **게시글 조회 (`GET /api/posts/{postId}`)**: 특정 게시글 조회  
- **게시글 삭제 (`DELETE /api/posts/{postId}`)**: 작성자가 자신의 게시글 삭제 가능  

&nbsp;

---

#### 3️⃣ 책 기능

- **책 발간 (`POST /api/books/publish`)**: 사용자가 여러 게시글을 묶어 책으로 출판  
- **책 조회 (`GET /api/books/{bookId}`)**: 특정 책 정보 조회
- **책과 게시글 관계 (1:N)**: 여러 게시글을 모아 하나의 책으로 구성하는 관계

&nbsp;

---

#### 4️⃣ 좋아요 기능 

- Entity상 만들어두었지만 아직 미구현 

&nbsp;

---

#### 5️⃣ 댓글 기능

- Entity상 만들어두었지만 아직 미구현 


&nbsp;

---

#### 6️⃣ 게시글과 주제 관리

- 게시글에 주제 추가하는 기능 아직 미구현
- **게시글과 주제(N:N) 관계**  

&nbsp;

---

#### 7️⃣ 알림 및 이메일 발송 기능

- **구독한 작가의 새로운 게시글 알림**  
- **이메일 알림 (`Quartz` 스케줄러 활용)**  


  

&nbsp;

***
&nbsp;


