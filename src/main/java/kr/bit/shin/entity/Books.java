package kr.bit.shin.entity;

import jakarta.persistence.*;
import lombok.Data;

//자동으로 db테이블이 된다.
@Entity //하이버네이트가 books 클래스 -> 테이블로 변경해줌
@Data
public class Books {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql에서 auto_increment라는 뜻
    private Long id;    //id를 pk값으로 정하겠다.

    @Column(length = 50, nullable = false)  //컬럼 속성 설정 nullable = false -> not null/ length -> 길이 지정
    private String title;

    private int price;  // int타입 기본적으로  not null이다.
    private String writer;  //writer varchar(255)
    private int page;


    //JPA(Java Persistence API)
}
