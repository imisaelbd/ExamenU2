package mx.edu.ute.examenu2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.ute.examenu2.model.Book;
import mx.edu.ute.examenu2.service.BookService;
import mx.edu.ute.examenu2.utils.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class BookController {

    private final BookService service;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<Book>> create (@Valid @RequestBody Book book){
        try {
            CustomResponse<Book> response = service.create(book);
            HttpStatus statusCode = response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    response,
                    statusCode
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, true, 400, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<CustomResponse<java.util.List<Book>>> getAll () {
        try {
            CustomResponse<java.util.List<Book>> response = service.getAll();
            HttpStatus statusCode = response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    response,
                    statusCode
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, true, 400, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomResponse<Book>> getById (@PathVariable("id") String id) {
        try {
            CustomResponse<Book> response = service.getById(id);
            HttpStatus statusCode = response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    response,
                    statusCode
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, true, 400, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<Book>> update (@PathVariable("id") String id, @Valid @RequestBody Book book) {
        try {
            CustomResponse<Book> response = service.update(id, book);
            HttpStatus statusCode = response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    response,
                    statusCode
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, true, 400, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse<Book>> delete (@PathVariable("id") String id) {
        try {
            CustomResponse<Book> response = service.delete(id);
            HttpStatus statusCode = response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
            return new ResponseEntity<>(
                    response,
                    statusCode
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(
                            null, true, 400, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
