# Notification_Site
![image](https://github.com/swedu1team/NoticeSite/assets/85274249/f50f589c-3349-4c18-9207-b09618240c19)

# 역할
## Server[Pipeline&VM관리]
* 홍성재

## Security[SpringSecurity&OAuth]&DB관리
* 고병국 
* 이용준

## Frontend & DB설계 및 API 연동
* 이선호

## URL http://simplenoti.n-e.kr

# 01 소개


## 1. 프로젝트 목적 및 내용
* Simple Noti"는 동아리 임원 활동 중 느낀 불편함을 해결하기 위해 개발된 공지사항 서비스입니다. 예를 들어, 메신저를 통해 공지를 전달할 때 장소 정보를 별도로 안내해야 하는 번거로움이 있었습니다. 이러한 문제를 해결하기 위해 주소API와 지도API를 활용하여 세부 공지 사항과 장소 정보를 효과적으로 통합하여 제공하는 기능을 도입했습니다. 현재까지는 주로 위치 정보에 대한 불편함을 개선하는 데 초점을 맞추었지만, 앞으로는 여러 사이트에서 흔하게 겪지만 간과 되는 불편한 점을 찾아내 이를 효과적으로 해결하는 기능을 추가할 계획입니다. 하단은 서비스의 전반적인 구조를 사진으로 첨부하였습니다.

|로그인|공지사항리스트|공지사항작성|공지사항확인|
|:---:|:---:|:---:|:---:|
|![image](https://github.com/swedu1team/NoticeSite/assets/85274249/c710ae75-2356-457c-aa0a-98196240e876)|![image](https://github.com/swedu1team/NoticeSite/assets/85274249/f93bb80b-321c-4604-9b88-c4fd746d5d5f)|![image](https://github.com/swedu1team/NoticeSite/assets/85274249/d926f99e-f07f-4267-a33a-08cd02ac3666)|![image](https://github.com/swedu1team/NoticeSite/assets/85274249/ab4fa8c6-085e-49a0-905c-1b3bf5de3bf9)|

* 프로젝트의 주요 가치는 '사용자 중심 접근'입니다. 복잡한 디자인과 사용자 경험은 다양한 연령대의 사용자들이 서비스를 어렵게 느낄 수 있으므로 간결하고 직관적인 디자인을 중요시했습니다. 기술적인 측면에서는 원격 레포지토리에 Push되는 Jar 패키지가 자동으로 클라우드 인스턴스로 배포되는 CI/CD 절차를 도입하여 더욱 효율적인 운영을 지향했습니다. 또한, OAuth 및 사용자 정보 관리를 통해 보안성을 강화하고, 서버 내 DB 인증을 통해 안정적인 서비스를 제공하려 노력했습니다.

* 계획으로는 사용자들이 자주 방문하는 장소의 위치 정보를 크롤링을 통해 자동으로 수집하여 제공하는 기능을 추가할 예정입니다. 또한, 송금 기능도 도입하여 서비스의 활용도를 더욱 확장할 계획입니다. 데이터 양이 증가하면 초기의 모놀리틱 아키텍처에서 벗어나 각 기능별 독립된 DB 시스템을 구축하여 확장성과 효율성을 높일 계획입니다.


## 2. 사용 스택
![image](https://github.com/swedu1team/NoticeSite/assets/85274249/5c1af36a-6e6f-47ea-8c5c-d8ccfd7ecc03)

***

# 02 UML&DB설계

## DB Schema
![image](https://github.com/swedu1team/NoticeSite/assets/85274249/a6c9fd9f-1632-418c-af9c-00779f5f27ed)
## Development
![image](https://github.com/swedu1team/NoticeSite/assets/85274249/a4673623-2a25-4c10-bfc6-4048b0d5927c)
## Operation
![image](https://github.com/swedu1team/NoticeSite/assets/85274249/cce23230-9be0-446b-990a-b1a9d6b4280c)
***
