package kr.bit.shin.repository;

import kr.bit.shin.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    //books(Object(엔티티)) ---- books(Table)

        //내가 데이터를 수정하고 있다.
        //다른 친구가 수정하고 있으면 내가 수정하기 전에 수정완료하면


    //JpaRepository : crud 관련 메소드 제공됨.

}
