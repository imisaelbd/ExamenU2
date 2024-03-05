package mx.edu.ute.examenu2.service;

import lombok.RequiredArgsConstructor;
import mx.edu.ute.examenu2.model.Book;
import mx.edu.ute.examenu2.model.BookRepository;
import mx.edu.ute.examenu2.utils.CustomResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional

public class BookService {

    private final BookRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<Book> create (Book book) {
        String id = UUID.randomUUID().toString();
        String newId = id.substring(0, 7);
        String urlBase = "https://picsum.photos/600/300/?image=";
        int num = (int) (Math.random() * 100);
        String url = urlBase + num;
        book.setId(newId);
        book.setImage(url);
        Optional<Book> optionalTicket = repository.findById(newId);
        if (optionalTicket.isPresent()) {
            return new CustomResponse<>(
                    null, true, 400, "El libro ingresado ya se encuentra registrada!"
            );
        } else {
            book.setId(newId);
            Book newTicket = repository.save(book);
            return new CustomResponse<>(
                    newTicket, false, 200, "Libro registrada correctamente!"
            );
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAll () {
        List<Book> books = repository.findAll();
        if (books.isEmpty()){
            return new CustomResponse<>(
                    null, true, 400, "No hay libros registrados!"
            );
        } else {
            return new CustomResponse<>(
                    books, false, 200, "Libros encontrados!"
            );
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<Book> getById(String id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            return new CustomResponse<>(
                    book, false, 200, "Libro encontrado!"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "El id del libro ingresado no existe!"
            );
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<Book> update (String id, Book book) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()){
            Book bookToUpdate = optionalBook.get();
            BeanUtils.copyProperties(book, bookToUpdate, "id");
            Book updatedBook = repository.save(bookToUpdate);
            return new CustomResponse<>(
                    updatedBook, false, 200, "Libro actualizado correctamente!"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "El id del libro ingresado no existe!"
            );
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public CustomResponse<Book> delete (String id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            repository.deleteById(id);
            return new CustomResponse<>(
                    book, false, 200, "Libro eliminado correctamente!"
            );
        } else {
            return new CustomResponse<>(
                    null, true, 400, "El id del libro ingresado no existe!"
            );
        }
    }
}
