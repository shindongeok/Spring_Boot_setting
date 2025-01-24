package kr.bit.shin.controller;

import kr.bit.shin.entity.Books;
import kr.bit.shin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestController {


    @Autowired
    private BookService bookService;

    //클라이언트가 서버로부터 받은 응답에서 책리스트랑 상태코드를 다 알수 있다.
    //ResponseEntity ->
    @GetMapping("/list")
    public ResponseEntity<List<Books>> getList(){
        return new ResponseEntity<>(bookService.getList(), HttpStatus.OK);
    }

    //@RequestBody -> 클라이언트 요청
    @PostMapping("/list")
    public ResponseEntity<Books> register(@RequestBody Books books){
        return new ResponseEntity<>(bookService.register(books), HttpStatus.OK);
    }


    //클라이언트가 보낸 json형태의 책 데이터를 받아  -> Books객체로 변환함
    //책 디비에 삽입하고 상태코드가 200번과 같이 클라이언트한테 응답한다.

    //특정 id로 조회하기
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){    //@PathVariable Long id 주입
        try{
            return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(id + "찾을수가 없습니다.", HttpStatus.NOT_FOUND);   //404
        }
    }

    @PutMapping("/list/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id,
                                            @RequestBody Books books){    //@PathVariable Long id 주입


        try{
            Books books1 = bookService.update(id, books);
            return ResponseEntity.ok(books1);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
//            return new ResponseEntity<>(id + "찾을수가 없습니다.", HttpStatus.NOT_FOUND);   //404
        }
    }


    //자료가값이 정해지지 않아서 Long값도 들어올수 있다.
    @DeleteMapping("/list/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        try {
             bookService.deleteById(id);
             return ResponseEntity.ok("성공: " + id);
        }catch (RuntimeException e){
//            HttpStatus.INTERNAL_SERVER_ERROR = 500 에러 반환하겠다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(id + " " + e.getMessage());
                    .body("Delete id : " +id);
        }
    }


}
