package service.impl;

import dto.BookDTO;
import mapper.BookMapper;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }
    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Book Not Found"));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setCategory(bookDTO.getCategory());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setPublisher(bookDTO.getPublisher());
        existingBook.setPublishedYear(bookDTO.getPublishedYear());
        existingBook.setTotalCopies(bookDTO.getTotalCopies());
        existingBook.setAvailableCopies(bookDTO.getAvailableCopies());

        Book updated =  bookRepository.save(existingBook);
        return BookMapper.toDTO(updated);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
