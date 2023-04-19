### 개발환경
    
+   IDE : Eclipse
+   Spring boot 2.7.10
+   JPA
+   Mysql 8

------------
### 개발 참고
    
+   인프런 : 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발

------------
### jar build
1.  프로젝트 경로 이동
2.  ./gradlew clean build
3.  cd build/libs
4.  java -jar lib파일명

------------
### Entity생성시 주의점
1.  가급적 Setter를 쓰지말자, 변경포인트가 너무 많아서 유지보수가 어렵다.
2.  모든 연관관계는 지연로딩으로 설정 -> 즉시로딩은 예측이 어렵고, 어떤 SQL이 실행될지 추적이 어렵다.
3.  @OneToOne, @ManyToOne은 기본이 즉시로딩이므로 지연로딩으로 설정한다.
4.  컬렉션은 필드에서 초기화를 해주자
