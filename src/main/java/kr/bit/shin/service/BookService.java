package kr.bit.shin.service;


import kr.bit.shin.entity.Books;
import kr.bit.shin.repository.BookRepository;
import lombok.experimental.Delegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.awt.print.Book;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

//Repository -> Service -> Controller
@Service
public class BookService {

    //로그 기록 남겨준다.
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;
    //R
    public List<Books> getList(){
        return bookRepository.findAll();    //select * from books;
    }
    //C 삽입
    public Books register(Books books){
        return bookRepository.save(books);
    }
    //R ->  특정 데이터 가져오기
    public Books getById(Long id){
        //id있으면 책의 정보들이 optional에 들어있음
        Optional<Books> optional = bookRepository.findById(id);
        if(optional.isPresent()){   //존재하면
            return optional.get();  //optional에 들어있는 book 꺼내온다(id기준으로)
        }
        else{
            throw new RuntimeException(id + "책이 없다.");
        }
    }

    //수정할 데이터   update가 트랜잭션이 되면서(함수단위로 실행됨)
    @Transactional
    public Books update(Long id, Books books){
        Optional<Books> optional = bookRepository.findById(id);

        if(optional.isPresent()){
            Books books1 = optional.get();  //지금 db에 있는 객체 (영속성 메모리에 있음).
            books1.setTitle(books.getTitle());  //변경하겠다!.  테이블에 있는 title 값 변경
            books1.setPrice(books.getPrice()); //테이블에 있는 price 값 변경
            //book객체가 변경이되면 자동으로 db에 반영이 됨.(더티체킹 - dirty checking)
            //entity 객체는 값이 변경되면서 자동으로 update 쿼리 실행됨 -> save 호출을 따로 안해도됨.
            return books1;  //update 실행
        }
        else{
            throw new RuntimeException(id + "찾을수 없다.");
        }
    }

//    //D
//    public void deleteById(Long id){
//        bookRepository.deleteById(id);
//    }

    @Transactional
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) { // ID가 존재하지 않는 경우
            //존재하는 아이디가 없으면 콘솔에 보여줌.
            logger.error("ID " + id + "는 존재하지 않습니다.");
            throw new RuntimeException("ID " + id + "는 존재하지 않습니다.");
        }
        bookRepository.deleteById(id); // ID가 존재할 경우 삭제
    }


}
