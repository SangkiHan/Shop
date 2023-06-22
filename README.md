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

### 테이블 전략
    dto : com.spring.study.dto

    @SequenceGenerator : 시퀀스객체를 사용하여 ID값을 자동생성하게 하는 어노테이션
    @TableGenerator : 테이블을 사용하여 ID값을 생성하는 어노테이션
------------
### 데이터중심설계법
    dto : com.spring.study.practice.data.dto

    @Id : 테이블의 키값을 지정한다.
    @GeneratedValue
        AUTO : 자동위임(auto_increment),
        IDENTITY : 기본키생성을 DB에 위임
            pk가 null로 들어갈시 pk값 생성 그러므로 insert시 영속이 되면 바로 쿼리실행해버림, 
            단점 : DB에 넣어봐야 PK가 어떻게 들어갔는지 알수있음

    테이블을 중점을 두기않고 객체에 중점을 두어 설계한 방식, 실무에서는 이렇게 사용하지않는다.
------------
### 테이블 연관관계 설계법

    dto : com.spring.study.practice.relation.dto

    @ManyToOne : 다대일
    @OneToMany : 일대다
    @JoinColumn: Join하는 상대 컬럼명

    테이블끼리는 방향이라는 개념이 없기 때문에 Entity에 @ManyToOne만 작성하여도 문제는 없다.
    @OneToMany는 개발을 하다가 필요할 때 사용하면된다.


------------
### 고급매핑

    dto : com.spring.study.strategy.dto

    @MappedSuperclass : 등록자, 등록일과 같은 모든 테이블에 공통적으로 들어가는 컬럼을 객체로 만들어 선언해준다. 테이블마다 extends하여 사용가능
    @DiscriminatorValue : 부모테이블 DTYPE에 어떤 값으로 들어갈지 정하는 어노테이션
    @DiscriminatorColumn : 어떤 자식테이블에서 insert된 데이터인지 구분하는 컬럼 기본값은 DTYPE이다.
    @Inheritance : 자식테이블과의 조인 전략을 선언해준다.
        JOINED : 조인전략 -> 각각의 테이블로 설계, 
        SINGLE_TABLE : 단일테이블전략 -> 한 ITEM테이블로 다른테이블의 컬럼을 다 때려넣음 장점 : 성능이 잘나옴, 
        TABLE_PER_CLASS : 구현 클래스마다 테이블전략 (실무에서는 사용하지 않는다.)

------------
### 프록시

    Test class : com.spring.study.ProxyPractice

    getReference() : 데이터베이스 조회를 미루는 가짜 엔티티 객체를 조회 한다.
	실제 클래스와 겉모양이 똑같다.
	프록시 객체를 호출하면 실제 엔티티를 조회한다.
        1. 처음사용할 때 한번만 초기화한다.
        2. 프록시 객체가 실제 엔티티 객체로 변하는건 아님, 실제 엔티티객체에 접근가능하게 한다.
        3. 프록시 객체는 실제 엔티티 객체를 상속받는 객체다. 타입 비교시 instance of 사용
        . 이미 가져와서 영속성컨텍스트에 이미 실제 엔티티가 있으면 그 다음에 getReference 호출시 실제 엔티티를 가져온다.
        5. 영속성 Context를 지우면 프록시 에러가 발생한다.

``` JAVA
        emf.getPersistenceUnitUtil().isLoaded(m2); //프록시 인스턴스의 초기화 여부 확인
        m2.getClass().getName();  //프록시 클래스 확인
        Hibernate.initialize(m2); //프록시 강제초기화
```

------------
### 지연로딩, 즉시로딩

    Test class : com.spring.study.FetchPractice

    @ManyToOne(fetch = FetchType.LAZY) : 지연로딩
        지연로딩으로 가져올시 직접 참초된 객체를 get해오지않으면 프록시 객체로 가져온다.
        직접 프록시객체에 접근할 시 그때 실제 Entity를 조회해 온다.
	
	@ManyToOne(fetch = FetchType.EAGER) : 즉시로딩
	    두 객체를 함께 같이 자주 사용한다면 즉시로딩으로 실제 Entity를 조회해온다.
	
	    단점
	    1. 즉시로딩을 사용하면 예상치 못한 SQL 문제가 발생한다. 
	    2. JPQL에서 N+1 문제를 일으킨다. JPQL은 실제 입력한 쿼리를 실행한다. 
            그러면 실행한 다음 즉시로딩으로 되어있는 객체를 다시 한번 조회하기 때문에
	        select문이 즉시로딩 설정 되어있는 객체수만큼 실행될수가 있다.
	
	@ManyToOne, @OneToOne은 기본이 즉시로딩 설정이나 그러니 LAZY설정을 해주자
	@OneToMany, @ManyToMany는 기본이 지연로딩이다.

``` JAVA
    Team team = new Team();
    team.setName("A");
    
    Member member1 = new Member();
    member1.setUsername("A");
    member1.setTeam(team);
    
    em.persist(team);
    em.persist(member1);
    
    em.flush();
    em.clear();
    
    Member findmember = em.find(Member.class, member1.getId());
    
    System.out.println("Team class : "+ findmember.getTeam().getClass()); //프록시
    System.out.println("Team : "+ findmember.getTeam()); //직접 Team에 접근시 실제 Entity
    
    tx.commit();
```
------------
### 영속성 전이, 고아객체

    Test class : com.spring.study.CascadePractice
    dto : com.spring.study.cascade.dto

    영속성 전이
        부모객체에 자식객체가 들어있으면 부모객체만 persist하더라도 자식객체도 다 insert를 해준다.
        cascade = CascadeType.ALL
            ALL : 모두적용
            PERSIST : 영속
            REMOVE : 삭제
            MERGE : 병합
	
	고아객체
        orphanRemoval = true
        부모객체에서 자식객체가 삭제되면 자식테이블에 데이터가 삭제된다.
	
	주의사항
	자식테이블 객체가 단일소유자일 때만 사용한다.
	
	EX) 계시판테이블과 첨부파일 테이블이 있다면 첨부파일이 계시판하나에서만 관리되기 때문에  cascade를 써도되지만
        첨부파일이 만약에 여러 계시판에서 관리된다면 쓰면 안된다.

``` JAVA
    Parents parents = new Parents();
    parents.setName("A");
    
    Child child1 = new Child();
    Child child2 = new Child();
    
    child1.setName("A");
    child2.setName("B");
    
    parents.addChild(child1);
    parents.addChild(child2);
    
    em.persist(parents); //child를 굳이 persist하지 않아도 insert가 된다.
    
    em.flush();
    em.clear();
    
    Parents findParents = em.find(Parents.class, parents.getId());
    findParents.getChilds().remove(0);	//orphanRemoval를 true로 활성화시 객체에서 child를 지우면 delete문이 나간다.
    
    tx.commit();
```


------------
### 임베디드 속성

	Test class : com.spring.study.EmbededPractice
    dto : com.spring.study.embededCollection.dto

    @Embedded : 객체를 가져다쓰는 쪽에 세팅
    @Embeddable : 공통적으로 쓰는 객체에 세팅


``` JAVA
    @Embeddable
    @Getter
    @Setter
    public class Address {
        private String adres;
        private String dtladres;
        private String zipcode;
    }

    @Entity
    @Getter
    @Setter
    public class Member{
        @Id
        @GeneratedValue
        @Column(name = "MEMBER_ID")
        private Long Id;
        @Column(name = "USERNAME")
        private String username;
        @ManyToOne(fetch = FetchType.LAZY) //다대일
        @JoinColumn(name = "TEAM_ID")//JOIN하는컬럼명
        private Team team;
        @Embedded
        private Address address;
    }
```
    @AttributeOverrides : 같은 @Embeded를 사용할 시 따로 컬럼을 매핑해줄수있는 어노테이션
``` JAVA
    @Embedded
	private Address homeaddress;
	
	@Embedded
	@AttributeOverrides({
        @AttributeOverride(name="adres", 
                           column=@Column(name = "WORK_ADRES")),
        @AttributeOverride(name="dtladres", 
                           column=@Column(name = "WORK_DTLADRES")),
        @AttributeOverride(name="zipcode", 
        					column=@Column(name = "WORK_ZIPCODE"))
	})
	private Address workaddress;
```

    같은 Embeded타입을 여러 Entity에서 사용하면 위험하다
	대신 인스턴스를 복사해서 사용해야한다.
	
	인스턴스값들을 비교할때는 equals함수를 override하여 사용해야한다.
``` JAVA
    @Embeddable
    @Getter
    @Setter
    public class Address {
        
        private String adres;
        
        private String dtladres;
        
        private String zipcode;

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Address address = (Address) obj;
            return Objects.equals(adres, address.adres) &&
                    Objects.equals(dtladres, address.dtladres) &&
                    Objects.equals(zipcode, address.zipcode);
        }
    }
```
------------
### JPQL
    Test class : com.spring.study.JPQLPractice
    dto : com.spring.study.jpql.dto   

    JPA를 사용하면 Entity객체를 중심으로 개발해야함
	검색을 할 때도 테이블이 아닌 Entity객체를 대상으로 검색
	이러한 문제를 해결하기 위해 JPQL을 사용한다.

    JPQL특징
1.  Entity와 속성은 대소문자를 구별한다.
	2. JPQL키워드는 대소문자 구분X(SELECT, FROM, WHERE)
	3. 테이블이름이 아니라 엔티티이름을 사용한다.
	4. 별칭은 필수
    5. JPA는 WHERE, HAVING절에서만 서브 쿼리 사용가능
    6. FROM절에 서브쿼리는 사용 불가능

    
	TypeQuery : 반환 타입이 명확할 때 사용
	Query : 반환 타입이 명확하지 않을 때 사용

+	TypeQuery
``` JAVA
    TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
    List<Member> resultList = query.getResultList();			//list 가져올 때
    Member findmember = query.getSingleResult();				//One 가져올 때, 결과가 없거나 2개이상일시 Exception을 뱉는다.
```
+	Query
``` JAVA
    Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
	Member findemember = (Member) query.getSingleResult();
```
+	DTO로 값받기
``` JAVA
    List<MemberDTO> memberList = em.createQuery("SELECT new com.spring.study.jpql.dto.MemberDTO(m.username, m.age) FROM Member m").getResultList();
```
+	페이징처리
``` JAVA
    List<Member> pagingMember = em.createQuery("SELECT m FROM Member m", Member.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();
```
------------
### 경로표현식
+   상태필드: 경로 탐색의 끝, 탐색X
+   단일 값 연관 경로: 묵시적 내부 조인(inner join) 발생, 탐색 O
+   컬렉션 값 연관 경로: 묵시적 내부 조인 발생, 탐색 X
+   실무에서는 묵시적 내부 조인 사용X 튜닝이 힘듬 가급적 명시적 조인을 사용

``` SQL
    SELECT m.username
    FROM Member m
    join m.team t
    join m.orders o
    where t.name = '팀A'
```

### Fetch Join
+   SQL조인종류X
+   JPQL에서 성능 최적화를 위해 제공하는 기능
+   연관된 Entity나 Collection을 SQL한번에 함께 조회하는 기능
+   join fetch 명령어 사용
+   LAZY 지연로딩보다 join fetch가 우선이므로 즉시로딩이 된다.
``` SQL
    --SQL
    SELECT 
    M.*, T* 
    FROM
    MEMBER M
    INNER JOIN TEAM T
    ON M.TEAM_ID = T.ID

    --JPQL
    SELECT
    M
    FROM
    MEMBER M
    JOIN FETCH
    M.TEAM
```
    DISTINCT : 중복제거

    한계
+   Fetch Join대상에는 별칭X
+   둘 이상의 컬렉션은 페치 조인X
+   페치 조인을 하면 페이징 API를 사용X


### NamedQuery
1.  미리 정의해서 이름을 부여해두고 사용하는 JPQL
2.  정적 쿼리
3.  어노테이션 , XML에 정의
4.  애플리케이션 로딩 시점에 초기화 후 재사용
5.  애플리케이션 로딩 시점에 쿼리를 검증

``` Java

    //DTO
    @Entity
    @NamedQuery(
            name = "Member.findByUsername",
            query = "select m from Member m where m.username = :username"
    )
    public class Member extends BaseEntity{
        
        
        @Id
        @GeneratedValue	
        @Column(name = "MEMBER_ID")

    //@Repository
    public interface MemberRepository extends JpaRepository<User,Long>{

        @Query("select m from Member m where m.username = :username")
        Member findByUsername(String username);
    }

    //Use Example
    List<Member> resultList =  em.createNamedQuery("Member.findByUsername", Member.class)
					.setParameter("username", "회원1")
					.getResultList();
```