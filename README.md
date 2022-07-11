# Var-myeongso
개발자를 위한 작명 방식을 검색 서비스

---

### 프로젝트 배경

‘이름 짓기'의 중요성

- 개발자에게 이름 짓기는 많은 시간과 노력이 필요한 작업이다.
- 코드 맥락에 맞는 영어 표현을 찾는 것 자체도 문제지만, 그 표현이 문법에 맞는지, 보편적으로 사용되는지, 다른 사람이 읽어도 뜻을 알 수 있는 단어인지까지 고민해야 한다.

검색을 통해 도움을 받자

- 개발자들이 자주 사용되는 표현이나, 신뢰도 있는 프로젝트에서 사용된 작명 방식을 검색할 수 있는 서비스를 개발하였다.
<br>

### 서비스 소개

Var-myeongso란?

- 변수를 나타내는 표현 (Var)에 名所(명소)을 붙여, 개발자의 작명에 도움을 줄 수 있는 공간이라는 뜻이다.

기존 서비스

- 다양한 언어를 지원한다.
- 다양한 출처에서 결과를 가져온다.
- 검색 시 시간이 오래 걸린다.
- 단순 단어 검색만 가능하며 변수, 클래스, 메서드 별 검색은 불가하다.

차별성 및 기능

- 메서드나 클래스명 등의 검색 필터 추가
- 필터에 따른 검색 결과 개수 반환
- 역인덱싱 방식을 사용한 빠른 검색 속도
- 검색어가 어디서, 어떻게 사용되는지 알 수 있는 실제 코드와 출처 반환
- 자체적으로 정한 신뢰도 평가 방식으로 검색 결과를 정렬
<br>

### 기술 스택

- frontend 
  - <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white">
- backend 
  - <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/>
- infra 
  - <img src="https://img.shields.io/badge/ElasticStack-005571?style=flat&logo=ElasticStack&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white"/> <img src="https://img.shields.io/badge/Nginx-009639?style=flat&logo=Nginx&logoColor=white"/>
<br>

### 프로젝트 구조 - 관리자 사용 시나리오
<img width="762" alt="image" src="https://user-images.githubusercontent.com/70425484/178231363-800a88c8-e429-4b8f-86c2-a3f45033ef16.png">


- 서버에 저장되어 검색에 타겟이 될 코드를 지정
- Github, GitLab, Bitbucket 등 git 저장소 서비스에서 저장할 코드 url을 확인
- 이를 Var-myeongso 서비스에 지정하는 것으로 위와 같은 시나리오에 따라 서버에 불러옴.
- 코드 정보는 코드 검색의 대상이 되며 파싱, 인덱싱/역인덱싱을 거쳐 DB에 저장

### 프로젝트 구조 - 사용자 사용 시나리오
<img width="766" alt="image" src="https://user-images.githubusercontent.com/70425484/178231382-d0a67a07-81bf-463d-b15c-252ab16b9e72.png">


- 사용자는 단어 검색, 검색 결과 확인 가능
- 사용자는 검색하고자 하는 단어와 함께 사용처(변수, 메서드, 클래스, 패키지), 언어(검색 언어) 등의 필터링을 적용하여 검색
- 이런 사용자의 요구를 웹 애플리케이션은 HTTP 요청으로 변환하여 서버에 요청을 전달
- Spring boot로 제작된 서버 웹 애플리케이션은 이 요청을 해석하여 ELK 검색 툴에서 자료를 검색하고, 이를 다시 HTTP 응답으로 만들어 반환/화면에 출력하여 사용자는 검색된 결과물을 확인

### 프로젝트 구조 - 배포
<img width="704" alt="image" src="https://user-images.githubusercontent.com/70425484/178231400-8810f54d-f930-4de7-8e7a-682397cb1ea1.png">


- 전체 서비스를 외부 사용자 접근을 위해 배포
- 리액트로 제작된 프론트 웹 애플리케이션은 netlify로 배포하고, 개인 도메인을 구매하여 ‘[www.varmyeong.ecsimsw.com](http://www.varmyeong.ecsimsw.com/)’으로 접속 가능
- 서버는 개인 서버를 사용하여 웹 서버(Nginx), 백엔드 웹 애플리케이션(Spring boot), 검색 엔진(ELK)를 구동
- 설정이 까다로운 Nginx와 ELK는 Docker를 이용하여 다른 환경에 구애받지 않고 서버 환경을 구성할 수 있게 컨테이너화
<br>

### 사용 예시

- 메인 페이지
<img width="800" alt="image" src="https://user-images.githubusercontent.com/70425484/178231443-cd780bb5-19eb-4064-ba27-366c8487db7c.png">
    
    
- 기본 검색(Word): Word 검색은 검색어 ‘id’가 포함된 모든 코드가 검색된다.
<img width="800" alt="image" src="https://user-images.githubusercontent.com/70425484/178231470-ad9fd9c2-af2c-4b3a-9dbb-cb7b5e8be5c9.png">


- 변수명 검색: Variable 검색은 변수명이 ‘id’인 모든 코드가 검색된다.
<img width="800" alt="image" src="https://user-images.githubusercontent.com/70425484/178231499-2840e653-3606-48af-9682-0e6853be99ee.png">


- 클래스명 검색: Class 검색은 클래스명이 ‘HelloWorld’인 모든 코드가 검색된다.
<img width="800" alt="image" src="https://user-images.githubusercontent.com/70425484/178231528-bad024b9-f01f-4e12-a6ed-187d9b849ed6.png">
<br>

### 팀원
<table>
  <tr>
    <th><a href="https://github.com/ecsimsw">김진환</a></th>
    <th><a href="https://github.com/Yunkeun">조윤근</a></th>
    <th><a href="https://github.com/Jaeheon-So">소재헌</a></th>
  </tr>
  <tr>
    <td>back-end</td>
    <td>back-end</td>
    <td>front-end</td>
  </tr>
</table>
