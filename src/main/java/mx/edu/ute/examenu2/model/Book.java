package mx.edu.ute.examenu2.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")

public class Book {

    @Id
    private String id;

    @NotBlank(message = "El campo del título es obligatorio")
    private String title;

    @NotBlank(message = "El campo del autor es obligatorio")
    private String author;

    @NotBlank(message = "El campo de la fecha de publicación es obligatorio")
    private String date;

    private String image;

}
