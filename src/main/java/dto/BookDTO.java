package dto;

import lombok.Data;

@Data
public class BookDTO {
    private long id;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private String publisher;
    private int publishedYear;

    private int totalCopies;
    private int availableCopies;
}

